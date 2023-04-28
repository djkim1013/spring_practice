package com.example.drill.controller;

import com.example.drill.domain.dto.UserDto;
import com.example.drill.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TestController {

    final TestService testService;

    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnFavicon() {
    }

    @PostMapping("/test")
    @ResponseBody
    public void postUser(@RequestBody @Validated(UserDto.PostUserValidation.class) UserDto requestBody) {
        testService.postUser(requestBody);
    }

    @PutMapping("/test/{n}")
    @ResponseBody
    public void putUser(@PathVariable int n, @RequestBody @Validated(UserDto.PutUserValidation.class) UserDto requestBody) {
        requestBody.setUserId(n);
        testService.putUser(requestBody);
    }
}
