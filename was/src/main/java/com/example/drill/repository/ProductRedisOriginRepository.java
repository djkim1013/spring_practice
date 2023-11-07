package com.example.drill.repository;

import com.example.drill.domain.entity.MainProductRedisHashOrgin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRedisOriginRepository extends CrudRepository<MainProductRedisHashOrgin, Long> {
}
