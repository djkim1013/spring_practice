package com.example.drill.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_card")
public class CardDto {

    @Id
    private Long cardId;

    private String name;

    private float discountRate;
}
