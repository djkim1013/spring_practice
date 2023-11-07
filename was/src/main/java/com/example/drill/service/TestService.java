package com.example.drill.service;

import com.example.drill.domain.entity.MainProductRedisHash;
import com.example.drill.repository.ProductRedisRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ProductRedisRepository repository;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 1000*30L)
    public void testJsonString() throws JsonProcessingException{
        log.debug("start timer");
        LocalDateTime startTime = LocalDateTime.now();
        Set<String> keySet = redisTemplate.keys("mainProducts*").stream().filter(key -> key.matches("mainProducts:\\d{1,5}$")).collect(Collectors.toSet());
        Set<Long> keySetL = keySet.stream().map(key -> key.replaceAll("[^\\d]", "")).map(Long::valueOf).collect(Collectors.toSet());

        for (MainProductRedisHash value : repository.findAllById(keySetL)) {
            redisTemplate.opsForValue().set("mainProductJson:" + value.getMainProductId(), objectMapper.writeValueAsString(value));
        }
        Set<String> keySetJson = redisTemplate.keys("mainProductJson*");

        for(int i = 0; i < 50; i++) {
            List<Long> timeTable = new ArrayList<>();

            log.debug("reset timer");
            startTime = LocalDateTime.now();
            Iterable<MainProductRedisHash> productsList1 = repository.findAllById(keySetL);
            log.debug("crud repository findall : {}", productsList1);
            timeTable.add(Duration.between(startTime, LocalDateTime.now()).toMillis());
            log.debug("crud repository findall elapsed time :: {}", timeTable.get(0));

            log.debug("reset timer");
            startTime = LocalDateTime.now();
            List<MainProductRedisHash> productsList2 = new ArrayList<>();
            for (String key : keySetJson) {
                productsList2.add(objectMapper.readValue(redisTemplate.opsForValue().get(key), new TypeReference<MainProductRedisHash>() {
                }));
            }
            log.debug("template findall : {}", productsList2);
            timeTable.add(Duration.between(startTime, LocalDateTime.now()).toMillis());
            log.debug("resttemplate elapsed time :: {}", timeTable.get(1));
            log.info("repository : {} | template : {} | ratio : {}", timeTable.get(0), timeTable.get(1), String.format("%.2f", timeTable.get(0)*1.0/timeTable.get(1)));
        }
    }
}
