package com.example.drill.repository;

import com.example.drill.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<User,Integer> {
    List<User> findAll();

    User findByUserId(int userId);

    List<User> findAllByUserName(String userName);
}
