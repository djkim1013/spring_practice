package com.example.drill.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redis 설정
 */
@Configuration
public class RedisConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .deactivateDefaultTyping()
                .configure(MapperFeature.USE_ANNOTATIONS, false)
                .addModules(
                        new ParameterNamesModule(),
                        new Jdk8Module(),
                        new JavaTimeModule())
                .build();
    }
}

