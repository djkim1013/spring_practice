package com.example.drill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DrillApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrillApplication.class, args);
    }

}
