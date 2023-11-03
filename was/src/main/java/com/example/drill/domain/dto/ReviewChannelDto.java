package com.example.drill.domain.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReviewChannelDto implements Serializable {

    private long channelId;

    private String reviewChannelUrl;

    private String collectStatus;

    private LocalDateTime regDt;

    private LocalDateTime updDt;

    private String regId;

    private String updId;

}
