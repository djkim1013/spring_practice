package com.example.drill.service;

import com.example.drill.domain.dto.GetUserRequestDto;
import com.example.drill.domain.dto.GetUserResponseDto;
import com.example.drill.domain.dto.PostPutUserRequestDto;
import com.example.drill.domain.entity.User;
import com.example.drill.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpResponseException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    @Transactional(readOnly = true)
    public GetUserResponseDto getUser(GetUserRequestDto requestParams) throws HttpResponseException {
        if (requestParams.getUserId() != null) {
            return testRepository.findById(requestParams.getUserId())
                    .map(GetUserResponseDto::new)
                    .orElseThrow(() -> new HttpResponseException(HttpStatus.NOT_FOUND.value(), "사용자를 찾을 수 없습니다."));
        }
        return new GetUserResponseDto(testRepository.findAll(PageRequest.of(requestParams.getPage(), requestParams.getSize())));
    }

    @Transactional
    public void postUser(PostPutUserRequestDto requestBody) {
        testRepository.save(new User(requestBody.getUserName()));
    }

    @Transactional
    public void putUser(PostPutUserRequestDto requestBody) throws HttpResponseException {
        User user = testRepository.findById(requestBody.getUserId()).orElseThrow(() -> new HttpResponseException(HttpStatus.NOT_FOUND.value(), "사용자를 찾을 수 없습니다."));
        user.setUserName(requestBody.getUserName());
    }
}
