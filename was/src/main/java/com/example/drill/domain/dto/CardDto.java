package com.example.drill.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CardDto implements Serializable {
    private static final long serialVersionUID = -4576060534851240505L;

    private Long cardId;

    private String name;

    private float discountRate;
}
