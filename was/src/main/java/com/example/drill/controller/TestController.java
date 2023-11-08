package com.example.drill.controller;

import com.example.drill.domain.entity.MainProductRedisJson;
import com.example.drill.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService service;

    @GetMapping
    public MainProductRedisJson getOne(Long id){
        return service.getOne(id);
    }
}
