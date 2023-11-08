package com.example.drill.service;

import com.example.drill.domain.entity.MainProductRedisHash;
import com.example.drill.domain.entity.MainProductRedisHashOrgin;
import com.example.drill.domain.entity.MainProductRedisJson;
import com.example.drill.domain.mapper.ProductMapper;
import com.example.drill.repository.ProductRedisHashRepository;
import com.example.drill.repository.ProductRedisOriginRepository;
import com.example.drill.repository.ProductRedisStrRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ProductRedisHashRepository hashRepository;
    private final ProductRedisOriginRepository originRepository;
    private final ProductRedisStrRepository jsonRepository;
    private final ProductMapper productMapper;
    private final ObjectMapper objectMapper;

    public MainProductRedisJson getOne(String id){
        return jsonRepository.findById(id);
    }

//    @Scheduled(fixedRate = 1000 * 30L)
    public void testJsonString() throws JsonProcessingException {
        StopWatch stopWatch = StopWatch.createStarted();

        String originKey = "mainProducts:112660";
        String key = "mainProductJson:112660";
        Long keyL = 112660L;

        MainProductRedisHashOrgin originProduct = originRepository.findById(keyL).get();
        log.info("origin data {} :: {}ms", originProduct, stopWatch.getTime());
        stopWatch.reset();

        for (int i = 0; i < 10; i++) {
            stopWatch.start();
            for (int j = 0; j < 7000; j++) {
                redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(originProduct));
                MainProductRedisJson result = objectMapper.readValue(redisTemplate.opsForValue().get(key), MainProductRedisJson.class);
            }
            log.info("template : {}ms ", stopWatch.getTime());
            stopWatch.reset();

            stopWatch.start();
            for (int j = 0; j < 7000; j++) {
                hashRepository.save(productMapper.convertHash(originProduct));
                MainProductRedisHash result = hashRepository.findById(keyL).get();
            }
            log.info("hashRepository : {}ms", stopWatch.getTime());
            stopWatch.reset();
        }

    }
}
