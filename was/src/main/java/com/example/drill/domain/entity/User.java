package com.example.drill.domain.entity;

import lombok.*;
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
    int userId;

    @Column(name = "USER_NAME", length = 200, nullable = false)
    String userName;

    @Column(name = "UPDATE_TIMESTAMP")
    @UpdateTimestamp
    LocalDateTime updateDateTime;

    public User(String userName){
        this.userName = userName;
    }
}
