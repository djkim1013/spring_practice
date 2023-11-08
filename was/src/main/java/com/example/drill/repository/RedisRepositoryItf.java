package com.example.drill.repository;

public interface RedisRepositoryItf<T> {
    T findById(String id);

    T save(T data);
}
