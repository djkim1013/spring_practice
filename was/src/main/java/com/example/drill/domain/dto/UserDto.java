package com.example.drill.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@ToString
public class UserDto {

    public interface PostUserValidation {}
    public interface PutUserValidation {}

    @JsonIgnore
    Integer userId;

    @NotBlank
    String userName;

    @JsonIgnore
    LocalDateTime updateDateTime;
}
