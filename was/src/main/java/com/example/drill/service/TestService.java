package com.example.drill.service;

import com.example.drill.domain.dto.UserDto;
import com.example.drill.domain.entity.User;
import com.example.drill.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private TestService service;

    @Autowired
    public void setTestService(@Lazy TestService service) {
        this.service = service;
    }

    @Transactional
    public void postUser(UserDto requestBody) {
        testRepository.save(new User(requestBody.getUserName()));
    }

    @Transactional
    public void putUser_(UserDto requestBody) throws Throwable {
        User user = testRepository.findById(requestBody.getUserId()).orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
        user.setUserName(requestBody.getUserName());
        double randomNum = Math.random();
        if (randomNum > 1.0)
            throw new JpaSystemException(new RuntimeException("random intended exception - " + String.format("%1.2f", randomNum)));
    }

    public void putUser(UserDto requestBody) {
        try {
            service.putUser_(requestBody);
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    }
}
