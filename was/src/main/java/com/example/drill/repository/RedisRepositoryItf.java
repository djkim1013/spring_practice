package com.example.drill.repository;

public interface RedisRepositoryItf<T, ID> {
    T findById(ID id);

    T save(T data);
}
