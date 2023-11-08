package com.example.drill.repository;

import com.example.drill.domain.entity.MainProductRedisJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRedisStrRepository implements RedisRepositoryItf<MainProductRedisJson, String> {

    private final String ID_PREFIX = "mainProductJson:";
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public MainProductRedisJson findById(String id){
        return Optional.ofNullable(redisTemplate.opsForValue().get(getId(id)))
                .map(r -> {
                    try {
                        return objectMapper.readValue(r, MainProductRedisJson.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Redis Read Fail");
                    }
                })
                .orElseThrow(() -> new RuntimeException("Redis Not found"));
    }

    @Override
    public MainProductRedisJson save(MainProductRedisJson data) {
        try {
            redisTemplate.opsForValue().set(getId(data.getMainProductId()), objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Redis Save Fail");
        }
        return data;
    }

    private String getId(Long id){
        return getId(String.valueOf(id));
    }

    private String getId(String id){
        return ID_PREFIX + id;
    }
}
