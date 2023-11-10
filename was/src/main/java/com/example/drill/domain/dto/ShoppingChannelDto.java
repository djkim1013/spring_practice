package com.example.drill.domain.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
public class ShoppingChannelDto implements Serializable {

    @Id
    private long channelId;

    private String channelName;

    private String channelServiceId;

    private String channelLogoUrl;

    private String smallChannelLogoUrl;

    private int channelNum;

    private String channelURL;

    private String channelStatus;

    private String channelSearchStatus;

    private String collectStatus;

    private Integer collectInterval;

    private String mobileTvURL;

    private LocalDateTime regDt;

    private LocalDateTime updDt;

    private String regId;

    private String updId;

    private ReviewChannelDto reviewChannel;


}
