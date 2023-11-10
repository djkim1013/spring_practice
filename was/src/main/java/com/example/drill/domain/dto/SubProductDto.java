package com.example.drill.domain.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
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

    private Set<CardDto> cards;

    private Set<HostDto> showHosts;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubProductDto that = (SubProductDto) o;
        return Objects.equals(itemCd, that.itemCd)
                && Objects.equals(broadcastDt, that.broadcastDt) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime)
                && Objects.equals(productName, that.productName) && month == that.month && Objects.equals(price, that.price) && Objects.equals(orgPrice, that.orgPrice) && Objects.equals(productImgUrl, that.productImgUrl) && Objects.equals(shareUrl, that.shareUrl) && Objects.equals(shareDetailUrl, that.shareDetailUrl)

                && Objects.equals(productBrand, that.productBrand) && Objects.equals(programName, that.programName) && discountRate == that.discountRate
                && Objects.equals(soldoutYN, that.soldoutYN) && Objects.equals(saleYN, that.saleYN) && Objects.equals(freeShippingYN, that.freeShippingYN)

                && reviewCnt * 1.1 == that.reviewCnt && Float.compare(that.reviewScore, reviewScore) == 0
                && Objects.equals(freeReturnYN, that.freeReturnYN) && Objects.equals(freeSampleYN, that.freeSampleYN)

                // subProduct를 모두 삭제 후 새로 생성하여 넣고 매번 카테고리를 집어 넣으므로 최초 dto끼리 비교할 때는 제외시켜준다
                // && Objects.equals(category1, that.category1) && Objects.equals(category2, that.category2) && Objects.equals(category3, that.category3) && Objects.equals(category4, that.category4)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                itemCd,
                broadcastDt, startTime, endTime,
                reviewCnt, reviewScore,
                productName, price, orgPrice, month, productImgUrl, shareUrl, shareDetailUrl, productBrand, programName, discountRate,
                soldoutYN, saleYN, freeShippingYN, freeReturnYN, freeSampleYN
                // category1, category2, category3, category4
        );
    }

    @Builder
    public SubProductDto(Long subProductId, String itemCd, String broadcastDt, String startTime, String startDt, String endDt, String endTime, int priority, int reviewCnt, float reviewScore, String productName, Long price, Long orgPrice, int month, float discountRate, String ars, String productImgUrl, String shareUrl, String shareDetailUrl, String productBrand, String programName, Long category1, Long category2, Long category3, Long category4, String soldoutYN, String saleYN, String freeShippingYN, String freeReturnYN, String freeSampleYN, Set<CardDto> cards, Set<HostDto> showHosts) {
        this.subProductId = subProductId;
        this.itemCd = itemCd;
        this.broadcastDt = broadcastDt;
        this.startTime = startTime;
        this.startDt = startDt;
        this.endDt = endDt;
        this.endTime = endTime;
        this.priority = priority;
        this.reviewCnt = reviewCnt;
        this.reviewScore = reviewScore;
        this.productName = productName;
        this.price = price;
        this.orgPrice = orgPrice;
        this.month = month;
        this.discountRate = discountRate;
        this.ars = ars;
        this.productImgUrl = productImgUrl;
        this.shareUrl = shareUrl;
        this.shareDetailUrl = shareDetailUrl;
        this.productBrand = productBrand;
        this.programName = programName;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.category4 = category4;
        this.soldoutYN = soldoutYN;
        this.saleYN = saleYN;
        this.freeShippingYN = freeShippingYN;
        this.freeReturnYN = freeReturnYN;
        this.freeSampleYN = freeSampleYN;
        this.cards = cards;
        this.showHosts = showHosts;
    }
}
