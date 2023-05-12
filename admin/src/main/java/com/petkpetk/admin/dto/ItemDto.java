package com.petkpetk.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
	private Long id;
	private String itemName;
	private String itemDetail;
	private String itemStatus;
	private String imageUrl;
	private Long originalPrice;
	private Long discountRate;
	private Long price;
	private Long reviewCount;
	private Double totalRating;

}