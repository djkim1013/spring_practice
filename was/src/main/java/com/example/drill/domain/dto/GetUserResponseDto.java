package com.example.drill.domain.dto;

import com.example.drill.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserResponseDto {

    List<UserDto> userList;

    UserDto user;

    Integer page;

    Integer size;

    Long total;


    public GetUserResponseDto(User user) {
        this.user = new UserDto(user);
    }

    public GetUserResponseDto(Page<User> userList) {
        this.userList = userList.map(UserDto::new).getContent();
        this.page = userList.getPageable().getPageNumber();
        this.size = userList.getPageable().getPageSize();
        this.total = userList.getTotalElements();
    }
}
