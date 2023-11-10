package com.example.drill.repository;

import com.example.drill.domain.entity.MainProductRedisHash;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductRedisHashRepository implements RedisRepositoryItf<MainProductRedisHash, String> {

    private final String ID_PREFIX = "mainProducts:";
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public MainProductRedisHash findById(String id) {
        return objectMapper.convertValue(redisTemplate.opsForHash().entries(getId(id)), MainProductRedisHash.class);
    }

    @Override
    public MainProductRedisHash save(MainProductRedisHash data) {
        redisTemplate.opsForHash().putAll(getId(data.getMainProductId()), objectMapper.convertValue(data, Map.class));
        return data;
    }

    private String getId(Long id) {
        return getId(String.valueOf(id));
    }

    private String getId(String id) {
        return ID_PREFIX + id;
    }
}
