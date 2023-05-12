package com.petkpetk.service.domain.shopping.dto.item;

import com.petkpetk.service.domain.shopping.constant.ItemStatus;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class MainItemDto {
    private Long id;
    private String itemName;
    private String itemDetail;
    private ItemStatus itemStatus;
    private String imageUrl;
    private Long originalPrice;
    private Long discountRate;
    private Long price;
    private Long reviewCount;
    private Double totalRating;


    @QueryProjection
    public MainItemDto(Long id, String itemName, String itemDetail, ItemStatus itemStatus,
        String imageUrl,Long originalPrice, Double discountRate, Long price, Long reviewCount, Double totalRating) {
        this.id = id;
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.itemStatus = itemStatus;
        this.imageUrl = imageUrl;
        this.originalPrice = originalPrice;
        this.discountRate = (long)(discountRate*100);
        this.price = price;
        this.reviewCount = reviewCount;
        this.totalRating = totalRating;
    }
}
