package com.example.drill.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    private final RedisTemplate<String, String> redisTemplate;
//    private final ProductRedisRepository repository;

    @Scheduled(initialDelay = 10L, fixedRate = 10000L)
    public void testTemplate(){
        log.info("start timer");
        LocalDateTime startTime = LocalDateTime.now();
        List<Object> getResultMap = redisTemplate.opsForHash().multiGet("mainProducts", List.of("mainProductId", "productName"));
        log.info("redistemplate findall : {}", getResultMap);
        log.info("elapsed time :: {}", Duration.between(startTime, LocalDateTime.now()).toMillis());

//        log.info("reset timer");
//        startTime = LocalDateTime.now();
//        List<MainProductRedisHash> productsList = (List<MainProductRedisHash>) repository.findAll();
//        log.info("crud repository findall : {}", productsList);
//        log.info("elapsed time :: {}", Duration.between(startTime, LocalDateTime.now()).toMillis());
    }
}
