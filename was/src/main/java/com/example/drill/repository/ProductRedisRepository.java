package com.example.drill.repository;

import com.example.drill.domain.entity.MainProductRedisHash;
import org.springframework.data.repository.CrudRepository;

public interface ProductRedisRepository extends CrudRepository<MainProductRedisHash, Long> {
}
