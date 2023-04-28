package com.example.drill.domain.dto;

import com.example.drill.domain.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {

    Integer userId;

    String userName;

    LocalDateTime creationTimestamp;

    LocalDateTime updateTimestamp;

    public UserDto(User user){
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.creationTimestamp = user.getCreationTimestamp();
        this.updateTimestamp = user.getUpdateTimestamp();
    }
}
