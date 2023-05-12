package com.petkpetk.service.domain.shopping.repository.querydsl.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.petkpetk.service.domain.shopping.dto.item.ItemSearchDto;
import com.petkpetk.service.domain.shopping.dto.item.MainItemDto;
import com.petkpetk.service.domain.shopping.dto.item.ManageItemDto;
import com.petkpetk.service.domain.shopping.entity.item.Item;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<ManageItemDto> getManageList(ItemSearchDto itemSearchDto, Pageable pageable, String email);
}
