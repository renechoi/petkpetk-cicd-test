package com.petkpetk.service.domain.shopping.dto.item;

import java.time.LocalDateTime;

import com.petkpetk.service.domain.shopping.constant.ItemStatus;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class ManageItemDto {
	private Long id;
	private String itemName;
	private String itemDetail;
	private ItemStatus itemStatus;
	private String email;
	private String imageUrl;
	private Long price;
	private String createdBy;
	private LocalDateTime registeredAt;

	@QueryProjection
	public ManageItemDto(Long id, String itemName, String itemDetail, ItemStatus itemStatus,
		String email, String imageUrl, Long price, String createdBy, LocalDateTime registeredAt) {
		this.id = id;
		this.itemName = itemName;
		this.itemDetail = itemDetail;
		this.itemStatus = itemStatus;
		this.email = email;
		this.imageUrl = imageUrl;
		this.price = price;
		this.createdBy = createdBy;
		this.registeredAt = registeredAt;
	}
}
