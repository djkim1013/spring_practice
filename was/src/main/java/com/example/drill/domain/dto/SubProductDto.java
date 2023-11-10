package com.example.drill.domain.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
public class SubProductDto implements Serializable {
    private static final long serialVersionUID = -7484355224113278828L;

    @Id
    private Long subProductId;

    private String itemCd;

    private String broadcastDt;

    private String startTime;

    private String startDt;

    private String endDt;

    private String endTime;

    private int priority;

    private int reviewCnt;

    private float reviewScore;

    private String productName;

    private Long price;

    private Long orgPrice;

    private int month;

    private float discountRate;

    private String ars;

    private String productImgUrl;

    private String shareUrl;

    private String shareDetailUrl;

    private String productBrand;

    private String programName;

    private Long category1;

    private Long category2;

    private Long category3;

    private Long category4;

    private String soldoutYN;

    private String saleYN;

    private String freeShippingYN;

    private String freeReturnYN;

    private String freeSampleYN;

    @OneToMany(mappedBy = "cardId")
    private Set<CardDto> cards;

    @OneToMany(mappedBy = "hostId")
    private Set<HostDto> showHosts;


}
