package com.example.drill.repository;

import com.example.drill.domain.entity.MainProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<MainProduct, Long> {
}
