package com.example.drill.service;

import com.example.drill.domain.entity.MainProductRedisHash;
import com.example.drill.domain.entity.MainProductRedisHashOrgin;
import com.example.drill.domain.entity.MainProductRedisJson;
import com.example.drill.domain.mapper.ProductMapper;
import com.example.drill.repository.ProductRedisHashRepository;
import com.example.drill.repository.ProductRedisOriginRepository;
import com.example.drill.repository.ProductRedisStrRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    private final ProductRedisHashRepository hashRepository;
    private final ProductRedisOriginRepository originRepository;
    private final ProductRedisStrRepository jsonRepository;
    private final ProductMapper productMapper;

    public MainProductRedisJson getOne(Long id) {
        return jsonRepository.findById(String.valueOf(id));
    }

    @Scheduled(fixedRate = 1000 * 30L)
    public void testJsonString() throws JsonProcessingException {
        StopWatch stopWatch = StopWatch.createStarted();

        String keyL = "112660";

        MainProductRedisHashOrgin originProduct = originRepository.findById(keyL).get();
        log.info("origin data {} :: {}ms", originProduct, stopWatch.getTime());
        stopWatch.reset();

        for (int i = 0; i < 10; i++) {
            stopWatch.start();
            for (int j = 0; j < 7000; j++) {
                jsonRepository.save(productMapper.convertJson(originProduct));
                MainProductRedisJson result = jsonRepository.findById(keyL);
            }
            log.info("template : {}ms ", stopWatch.getTime());
            stopWatch.reset();

            stopWatch.start();
            for (int j = 0; j < 7000; j++) {
                hashRepository.save(productMapper.convertHash(originProduct));
                MainProductRedisHash result = hashRepository.findById(keyL);
            }
            log.info("hashRepository : {}ms", stopWatch.getTime());
            stopWatch.reset();
        }

    }
}
