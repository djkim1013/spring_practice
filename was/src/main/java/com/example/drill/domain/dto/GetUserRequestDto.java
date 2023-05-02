package com.example.drill.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class GetUserRequestDto {
    @NotNull
    @Min(0)
    @Max(Integer.MAX_VALUE - 1)
    Integer page = 0;

    @NotNull
    @Min(1)
    @Max(Integer.MAX_VALUE - 1)
    Integer size = 10;

    String userName;

    SearchType searchType = SearchType.NONE;

    @JsonIgnore
    Integer userId;

    public void setSearchType(String searchType) {
        try {
            this.searchType = SearchType.valueOf(searchType);
        } catch (NullPointerException | IllegalArgumentException e) {
            this.searchType = SearchType.NONE;
        }
    }

    public enum SearchType {
        NONE, START, END, ALL
    }
}
