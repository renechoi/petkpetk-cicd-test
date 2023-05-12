package com.petkpetk.admin.dto.response;

import java.util.List;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.ItemDto;
import com.petkpetk.admin.dto.UserAccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

	private Long id;
	private String itemName;
	private String itemDetail;
	private String itemStatus;
	private Long originalPrice;
	private Long discountRate;
	private Long price;
	private Long reviewCount;
	private Double totalRating;

	public static ItemResponse from(ItemDto dto) {
		return EntityAndDtoConverter.convertToDto(dto, ItemResponse.class);
	}
}
