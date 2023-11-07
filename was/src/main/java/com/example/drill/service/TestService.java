package com.example.drill.service;

import com.example.drill.domain.entity.MainProductRedisHash;
import com.example.drill.domain.entity.MainProductRedisHashOrgin;
import com.example.drill.domain.entity.MainProductRedisJson;
import com.example.drill.domain.mapper.ProductMapper;
import com.example.drill.repository.hash.ProductRedisHashRepository;
import com.example.drill.repository.hash.ProductRedisOriginRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ProductRedisHashRepository repository;
    private final ProductRedisOriginRepository originRepository;
    private final ProductMapper productMapper;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 1000 * 30L)
    public void testJsonString() throws JsonProcessingException {
        StopWatch stopWatch = StopWatch.createStarted();

        Set<String> keySet = Objects.requireNonNull(redisTemplate.keys("mainProducts*")).stream().filter(key -> key.matches("mainProducts:\\d{1,5}$")).collect(Collectors.toSet());
        Set<Long> keySetL = keySet.stream().map(key -> key.replaceAll("[^\\d]", "")).map(Long::valueOf).collect(Collectors.toSet());

        log.info("got keySet {} :: {}ms", keySetL.size(), stopWatch.getTime());
        Set<MainProductRedisHashOrgin> originProductList = new HashSet<>();
        originRepository.findAllById(keySetL).forEach(originProductList::add);
        log.info("got data {} :: {}ms", originProductList.size(), stopWatch.getTime());
        stopWatch.reset();

        for (int i = 0; i < 10; i++) {
            stopWatch.start();
            for (MainProductRedisHashOrgin value : originProductList) {
                redisTemplate.opsForValue().set("mainProductJson:" + value.getMainProductId(), objectMapper.writeValueAsString(value));
            }
            long time1 = stopWatch.getTime();
            stopWatch.reset();
            stopWatch.start();
            Set<String> keySetJson = Objects.requireNonNull(redisTemplate.keys("mainProductJson*")).stream().filter(key -> key.matches("mainProductJson:\\d{1,5}$")).collect(Collectors.toSet());
            List<MainProductRedisJson> resultList1 = new ArrayList<>();
            for (String key : keySetJson) {
                resultList1.add(objectMapper.readValue(redisTemplate.opsForValue().get(key), MainProductRedisJson.class));
            }
            log.info("template write : {}ms | read : {}ms", time1, stopWatch.getTime());
            stopWatch.reset();

            stopWatch.start();
            for (MainProductRedisHashOrgin value : originProductList) {
                repository.save(productMapper.convertHash(value));
            }
            time1 = stopWatch.getTime();
            stopWatch.reset();
            stopWatch.start();
            List<MainProductRedisHash> resultList2 = new ArrayList<>();
            repository.findAllById(keySetL).forEach(resultList2::add);
            log.info("hashRepository write : {}ms | read : {}ms", time1, stopWatch.getTime());
            stopWatch.reset();

            stopWatch.start();
            for (MainProductRedisHashOrgin value : originProductList) {
                repository.save(productMapper.convertHash(value));
            }
            time1 = stopWatch.getTime();
            stopWatch.reset();
            stopWatch.start();
            List<MainProductRedisHash> resultList3 = new ArrayList<>();
            for(Long key: keySetL) {
                resultList3.add(repository.findById(key).get());
            }
            log.info("hashRepository write : {}ms | read : {}ms", time1, stopWatch.getTime());
            stopWatch.reset();
        }

    }
}
