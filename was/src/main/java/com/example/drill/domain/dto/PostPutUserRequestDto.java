package com.example.drill.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Data
public class PostPutUserRequestDto {


    public interface PostUserValidation {
    }

    public interface PutUserValidation {
    }

    @JsonIgnore
    Integer userId;

    @NotBlank
    String userName;
}
