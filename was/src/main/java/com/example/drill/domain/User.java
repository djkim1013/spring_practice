package com.example.drill.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
@ToString
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "USER_ID")
    Integer userId;

    @Column(name = "USER_NAME")
    String userName;
}
