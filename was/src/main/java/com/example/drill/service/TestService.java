package com.example.drill.service;

import com.example.drill.domain.User;
import com.example.drill.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private TestService service;

    @Autowired
    public void setTestService(@Lazy TestService service) {
        this.service = service;
    }

    public void postNUsers(int n) {
        for (int i = 0; i < n; i++) {
            service.postUser(i);
        }
    }

    @Transactional
    protected void postUser(int i) {
        testRepository.save(new User(String.format("User%03d", i + 1)));
    }

    public void putUsers(Integer n) {
        for (int i = 0; i < n; i++) {
            service.putUser(i);
        }
    }

    @Transactional
    protected void putUser(int i) {
        testRepository.findByUserId(i);
    }
}
