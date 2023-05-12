package com.petkpetk.service.domain.shopping.dto.item;

import com.petkpetk.service.domain.shopping.constant.ItemStatus;

import lombok.Data;

@Data
public class ItemSearchDto {
    private String searchDateType;

    private ItemStatus searchItemStatus;

    private String searchBy;

    private String searchQuery;
}
