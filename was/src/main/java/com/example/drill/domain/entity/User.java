package com.example.drill.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "USER")
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "USER_ID")
    Integer userId;

    @Column(name = "USER_NAME", length = 200, nullable = false)
    String userName;

    @Column(name = "CREATION_TIMESTAMP")
    @CreationTimestamp
    LocalDateTime creationTimestamp;

    @Column(name = "UPDATE_TIMESTAMP")
    @UpdateTimestamp
    LocalDateTime updateTimestamp;

    public User(String userName){
        this.userName = userName;
    }
}
