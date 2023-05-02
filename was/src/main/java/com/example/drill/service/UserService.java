package com.example.drill.service;

import com.example.drill.domain.dto.GetUserRequestDto;
import com.example.drill.domain.dto.GetUserResponseDto;
import com.example.drill.domain.dto.PostPutUserRequestDto;
import com.example.drill.domain.entity.User;
import com.example.drill.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpResponseException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public GetUserResponseDto getUser(GetUserRequestDto requestParams) throws HttpResponseException {
        if (requestParams.getUserId() != null) {
            return userRepository.findById(requestParams.getUserId())
                    .map(GetUserResponseDto::new)
                    .orElseThrow(() -> new HttpResponseException(HttpStatus.NOT_FOUND.value(), "사용자를 찾을 수 없습니다."));
        }else if (requestParams.getUserName() != null && requestParams.getUserName().trim().length() > 0){
            ExampleMatcher exampleMatcher = ExampleMatcher.matching();
            Example<User> example = Example.of(new User(requestParams.getUserName()), exampleMatcher);
            return new GetUserResponseDto(userRepository.findAll(example, PageRequest.of(requestParams.getPage(), requestParams.getSize())));
        }
        return new GetUserResponseDto(userRepository.findAll(PageRequest.of(requestParams.getPage(), requestParams.getSize())));
    }

    @Transactional
    public void postUser(PostPutUserRequestDto requestBody) {
        userRepository.save(new User(requestBody.getUserName()));
    }

    @Transactional
    public void putUser(PostPutUserRequestDto requestBody) throws HttpResponseException {
        User user = userRepository.findById(requestBody.getUserId()).orElseThrow(() -> new HttpResponseException(HttpStatus.NOT_FOUND.value(), "사용자를 찾을 수 없습니다."));
        user.setUserName(requestBody.getUserName());
    }
}
