package com.example.drill;

//import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;

import com.redis.om.spring.annotations.EnableRedisEnhancedRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
//@EnableRedisRepositories(basePackages = "com.example.drill.repository.**")
@EnableRedisEnhancedRepositories(basePackages="com.example.drill.repository.hash")
//@EnableRedisDocumentRepositories(basePackages="com.example.drill.repository.doc")
public class DrillApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrillApplication.class, args);
    }

}
