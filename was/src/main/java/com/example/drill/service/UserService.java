package com.example.drill.service;

import com.example.drill.domain.dto.GetUserRequestDto;
import com.example.drill.domain.dto.GetUserResponseDto;
import com.example.drill.domain.dto.PostPutUserRequestDto;
import com.example.drill.domain.entity.User;
import com.example.drill.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public GetUserResponseDto getUser(GetUserRequestDto requestParams) {
        if (requestParams.getUserId() != null) {
            return userRepository.findById(requestParams.getUserId())
                    .map(GetUserResponseDto::new)
                    .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        }
        PageRequest pageRequest = PageRequest.of(requestParams.getPage(), requestParams.getSize());
        if (requestParams.getUserName() != null && requestParams.getUserName().trim().length() > 0) {
            ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase("userName");
            switch (requestParams.getSearchType()) {
                case ALL:
                    exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
                    break;
                case START:
                    exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
                    break;
                case END:
                    exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.ENDING);
                    break;
                case NONE:
                default:
            }
            Example<User> example = Example.of(new User(requestParams.getUserName()), exampleMatcher);
            return new GetUserResponseDto(userRepository.findAll(example, pageRequest));
        }
        return new GetUserResponseDto(userRepository.findAll(pageRequest));
    }

    @Transactional
    public void postUser(PostPutUserRequestDto requestBody) {
        userRepository.save(new User(requestBody.getUserName()));
    }

    @Transactional
    public void putUser(PostPutUserRequestDto requestBody) {
        User user = userRepository.findById(requestBody.getUserId()).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        user.setUserName(requestBody.getUserName());
    }
}
