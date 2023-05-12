package com.petkpetk.admin.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.request.ShoppingNoticeRegisterRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingNoticeDto {

	private Long id;
	private String title;
	private String content;
	private List<ShoppingNoticeImageDto> images;
	private LocalDateTime createdAt;

	public static ShoppingNoticeDto from(ShoppingNoticeRegisterRequest shoppingNoticeRegisterRequest) {
		return EntityAndDtoConverter.convertToDto(shoppingNoticeRegisterRequest, ShoppingNoticeDto.class);
	}
}
