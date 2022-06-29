package com.example.drill.controller;

import com.example.drill.domain.User;
import com.example.drill.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TestController {

    final TestService testService;

    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnFavicon(){}

    @GetMapping
    public String view(Model model){
        return "login";
    }

    @GetMapping("test/{userId}")
    @ResponseBody
    public void test(@PathVariable Integer userId, User user){
        testService.test(userId, user);
    }

    @GetMapping("test/add")
    @ResponseBody
    public void testAdd(){
        testService.testAdd();
    }
}
