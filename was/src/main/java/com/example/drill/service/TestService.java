package com.example.drill.service;

import com.example.drill.domain.entity.MainProductRedisHash;
import com.example.drill.repository.ProductRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

//    private final RedisTemplate<String, MainProductRedisHash> redisTemplate;
    private final ProductRedisRepository repository;

    public void testTemplate(){
        log.info("start timer");
        LocalDateTime startTime = LocalDateTime.now();
//        Set<String> keySet = redisTemplate.keys("mainProducts");
//        List<MainProductRedisHash> productsList = redisTemplate.opsForValue().multiGet(keySet);
//        log.info("redistemplate findall : {}", productsList);
        log.info("elapsed time :: {}", Duration.between(startTime, LocalDateTime.now()).toMillis());
        log.info("reset timer");
        startTime = LocalDateTime.now();
        List<MainProductRedisHash> productsList = (List<MainProductRedisHash>) repository.findAll();
        log.info("crud repository findall : {}", productsList);
        log.info("elapsed time :: {}", Duration.between(startTime, LocalDateTime.now()).toMillis());
    }
}
