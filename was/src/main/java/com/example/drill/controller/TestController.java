package com.example.drill.controller;

import com.example.drill.domain.dto.GetUserRequestDto;
import com.example.drill.domain.dto.GetUserResponseDto;
import com.example.drill.domain.dto.PostPutUserRequestDto;
import com.example.drill.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpResponseException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TestController {

    final UserService userService;

    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnFavicon() {
    }

    @GetMapping({"/user", "/user/{userId}"})
    @ResponseBody
    public GetUserResponseDto getUser(@PathVariable(required = false) Integer userId,
                                      @Validated GetUserRequestDto requestParams) throws HttpResponseException {
        requestParams.setUserId(userId);
        return userService.getUser(requestParams);
    }

    @PostMapping("/user")
    @ResponseBody
    public void postUser(@RequestBody @Validated(PostPutUserRequestDto.PostUserValidation.class) PostPutUserRequestDto requestBody) {
        userService.postUser(requestBody);
    }

    @PutMapping("/user/{userId}")
    @ResponseBody
    public void putUser(@PathVariable int userId, @RequestBody @Validated(PostPutUserRequestDto.PutUserValidation.class) PostPutUserRequestDto requestBody) throws HttpResponseException {
        requestBody.setUserId(userId);
        userService.putUser(requestBody);
    }
}
