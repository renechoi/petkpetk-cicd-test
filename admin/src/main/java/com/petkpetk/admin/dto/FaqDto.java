package com.petkpetk.admin.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.request.FaqRequest;
import com.petkpetk.admin.dto.request.ShoppingNoticeRegisterRequest;
import com.petkpetk.admin.entity.FaqCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaqDto {

	private Long id;
	private String title;
	private String content;
	private FaqCategoryDto faqCategory;
	private LocalDateTime createdAt;

	public static FaqDto from(FaqRequest fa) {
		return EntityAndDtoConverter.convertToDto(fa, FaqDto.class);
	}
}
