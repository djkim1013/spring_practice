package com.example.drill.repository;

import com.example.drill.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<User,Integer> {
}
