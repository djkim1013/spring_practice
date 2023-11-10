package com.example.drill.domain.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class HostDto implements Serializable {
    private static final long serialVersionUID = 296225382213238333L;

    @Id
    private Long hostId;

    private String hostName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HostDto)) return false;
        HostDto that = (HostDto) o;
        return Objects.equals(hostName, that.hostName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostName);
    }

    @Builder
    public HostDto(Long hostId, String hostName) {
        this.hostId = hostId;
        this.hostName = hostName;
    }
}
