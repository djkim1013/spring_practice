package com.example.drill.domain.mapper;

import com.example.drill.config.MapperConfig;
import com.example.drill.domain.entity.MainProductRedisHash;
import com.example.drill.domain.entity.MainProductRedisHashOrgin;
import com.example.drill.domain.entity.MainProductRedisJson;
import org.mapstruct.*;

@Mapper(config = MapperConfig.class)
public interface ProductMapper {

    MainProductRedisJson convertJson(MainProductRedisHashOrgin origin);

    MainProductRedisHash convertHash(MainProductRedisHashOrgin origin);
}
