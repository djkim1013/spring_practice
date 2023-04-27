package com.example.drill.controller;

import com.example.drill.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TestController {

    final TestService testService;

    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnFavicon() {
    }

    @PostMapping("/test/{n}")
    @ResponseBody
    public void postNUsers(@PathVariable Integer n) {
        testService.postNUsers(n);
    }

    @PutMapping("/test/{n}")
    @ResponseBody
    public void putUsers(@PathVariable Integer n) {
        testService.putUsers(n);
    }
}
