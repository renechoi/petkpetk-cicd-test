package com.petkpetk.admin.dto.response;

import java.time.LocalDateTime;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.entity.ShoppingNotice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingNoticeResponse {

	private Long id;
	private String title;
	private String content;
	private LocalDateTime createdAt;

	public static ShoppingNoticeResponse from(ShoppingNotice shoppingNotice) {
		return EntityAndDtoConverter.convertToDto(shoppingNotice, ShoppingNoticeResponse.class);
	}
}
