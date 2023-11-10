package com.example.drill.domain.entity;

import com.example.drill.domain.dto.CardDto;
import com.example.drill.domain.dto.HostDto;
import com.example.drill.domain.dto.ShoppingChannelDto;
import com.example.drill.domain.dto.SubProductDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_mainProducts")
@Getter
@Setter
@ToString
public class MainProduct implements Serializable {

    @Id
    private Long mainProductId;

    // 아이템 코드
    private String itemCd;

    // 방송 일자(yyyyMMdd)
    private String broadcastDt;

    // 시작 시간(hhmm)
    private String startTime;

    // 시작 일자(yyyyMMddhhmm)
    private String startDt;

    // 종료 시간(hhmm)
    private String endTime;

    // 종료 일자(yyyyMMddhhmm)
    private String endDt;

    // 상품평 수
    private Integer reviewCnt;

    // 상품 평점
    private float reviewScore;

    // 상품 이름
    private String productName;

    // 할인가
    private long price;

    // 원가
    private long orgPrice;

    // 무이자 개월수
    private int month;

    // 할인율
    private float discountRate;

    // ARS 전화번호
    private String ars;

    // 상품 이미지 URL
    private String productImgUrl;

    // 상품 공유 URL
    private String shareUrl;

    // 상품 상세 URL
    private String shareDetailUrl;

    // 상품 브랜드
    private String productBrand;

    // 프로그램명
    private String programName;

    // 카테고리 1
    private Long category1;

    // 카테고리 2
    private Long category2;

    // 카테고리 3
    private Long category3;

    // 카테고리 4
    private Long category4;

    // 판매중 여부
    private String saleYN;

    // 품절 여부
    private String soldoutYN;

    // 무료배송 여부
    private String freeShippingYN;

    // 무료반품 여부
    private String freeReturnYN;

    // 무료샘플 여부
    private String freeSampleYN;

    // 쇼핑채널 정보
    private ShoppingChannelDto shoppingChannel;

    // 카드 정보
    @OneToMany
    private List<CardDto> cards = new ArrayList<>();

    // 쇼호스트 정보
    @OneToMany
    private List<HostDto> showHosts = new ArrayList<>();

    // 서브 프로덕트 정보
    @OneToMany
    private List<SubProductDto> subProducts = new ArrayList<>();

}
