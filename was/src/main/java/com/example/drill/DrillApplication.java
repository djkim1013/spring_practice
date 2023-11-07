package com.example.drill;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@EnableRedisDocumentRepositories(basePackages = "com.example.drill.repository.*")
public class DrillApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrillApplication.class, args);
    }

}
