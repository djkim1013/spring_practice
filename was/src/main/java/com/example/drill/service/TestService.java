package com.example.drill.service;

import com.example.drill.domain.entity.MainProduct;
import com.example.drill.domain.entity.MainProductRedisJson;
import com.example.drill.repository.ProductRedisStrRepository;
import com.example.drill.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    private final ProductRedisStrRepository jsonRepository;
    private final ProductRepository repository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public MainProductRedisJson getOne(Long id) {
        return jsonRepository.findById(String.valueOf(id));
    }

    final String keyL = "112660";

    @Scheduled(fixedRate = 1000L)
    @Async
    @Transactional(readOnly = true)
    public void testRead() throws JsonProcessingException {
        MainProductRedisJson resultRedis = jsonRepository.findById(keyL);
        Optional<MainProduct> result = repository.findById(Long.valueOf(keyL));
        if(result.isPresent()) {
            log.info("read          {} | {}", result.get().getProductName(), resultRedis.getProductName());
        }else{
            log.info("not found");
        }
    }

    @Scheduled(fixedRate = 1000L)
    @Async
    @Transactional
    public void testWrite() throws JsonProcessingException, InterruptedException {
        Long randNo = Math.round(Math.random() * 1000);
        log.info("randNp : {}", randNo);
        MainProductRedisJson resultRedis = jsonRepository.findById(keyL);
        resultRedis.setProductName(String.format("product%d", randNo));
        jsonRepository.save(resultRedis);
        MainProduct result = repository.findById(Long.valueOf(keyL)).orElse(repository.save(objectMapper.readValue(redisTemplate.opsForValue().get("mainProductJson:" + keyL), MainProduct.class)));
        result.setProductName(String.format("product%d", randNo));
        if (randNo > 500L) {
            log.info("runtimeException  {}", result.getProductName());
            throw new RuntimeException();
        }
        log.info("write         {} | {}", result.getProductName(), resultRedis.getProductName());
    }
}
