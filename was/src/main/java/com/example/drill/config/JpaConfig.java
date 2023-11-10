package com.example.drill.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.example.drill.repository"})
@EntityScan(basePackages = {"com.example.drill.domain.**"})
@RequiredArgsConstructor
public class JpaConfig {
}
