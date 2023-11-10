package com.example.drill.service;

import com.example.drill.domain.entity.MainProductRedisJson;
import com.example.drill.repository.ProductRedisStrRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    private final ProductRedisStrRepository jsonRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public MainProductRedisJson getOne(Long id) {
        return jsonRepository.findById(String.valueOf(id));
    }

    final String keyL = "112660";

    @Scheduled(fixedRate = 100L)
    @Async
    @Transactional(readOnly = true)
    public void testRead() throws JsonProcessingException {
        MainProductRedisJson result = jsonRepository.findById(keyL);
        log.info("read          {}", result.getProductName());
    }

    @Scheduled(fixedRate = 1000L)
    @Async
    @Transactional("redisTransactionMng")
    public void testWrite() throws JsonProcessingException, InterruptedException {
        MainProductRedisJson result = jsonRepository.findById(keyL);
        Long randNo = Math.round(Math.random() * 1000);
        result.setProductName(String.format("product%d", randNo));
        log.info("write sleep   {}", result.getProductName());
//        jsonRepository.save(result);
        redisTemplate.execute(new SessionCallback<List<Object>>() {
            public List<Object> execute(RedisOperations redisOperations)throws DataAccessException {
                redisOperations.multi();
                try {
                    redisOperations.opsForValue().set("mainProductJson:" + result.getMainProductId(), objectMapper.writeValueAsString(result));
                    if (randNo > 500L) {
                        log.info("runtimeException  {}", result.getProductName());
                        throw new RuntimeException();
                    }
                    return redisOperations.exec();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        });
        log.info("write         {}", result.getProductName());
    }
}
