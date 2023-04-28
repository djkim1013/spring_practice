package com.example.drill.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class GetUserRequestDto {
    Integer page = 0;
    Integer size = 10;
    String userName;
    SearchType searchType = SearchType.NONE;

    @JsonIgnore
    Integer userId;

    enum SearchType{
        NONE, START, END, ALL
    }
}
