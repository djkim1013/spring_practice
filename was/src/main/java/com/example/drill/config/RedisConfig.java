package com.example.drill.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Redis 설정
 */
@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisProperties redisProperties;

    @Bean
    public JedisConnectionFactory redisConnectionFactory(){
        return new JedisConnectionFactory(new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort()));
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .deactivateDefaultTyping()
                .configure(MapperFeature.USE_ANNOTATIONS, false)
                .addModules(
                        new ParameterNamesModule(),
                        new JavaTimeModule()
                )
                .build();
    }
}

