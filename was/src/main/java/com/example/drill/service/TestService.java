package com.example.drill.service;

import com.example.drill.domain.User;
import com.example.drill.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;


    public void test(Integer userId, User userUpdate){
        if(userId == null){
            System.out.println(testRepository.findAll());
            return;
        }
        User user = testRepository.findByUserId(userId);
        System.out.println(user);
        user.setUserName(userUpdate.getUserName());
//        System.out.println(testRepository.findAllByUserName("updated User"));
//        testRepository.save(user);
        System.out.println(testRepository.findAllByUserName("updated User"));
    }

    public void testAdd() {
        testRepository.save(User.builder().userName("new User").build());
    }
}
