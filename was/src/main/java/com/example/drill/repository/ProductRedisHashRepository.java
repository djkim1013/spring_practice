package com.example.drill.repository;

import com.example.drill.domain.entity.MainProductRedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRedisHashRepository extends CrudRepository<MainProductRedisHash, Long> {
}
