package com.example.drill.domain.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_host")
public class HostDto{

    @Id
    private Long hostId;

    private String hostName;
}
