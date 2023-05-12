package com.petkpetk.admin.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.request.FaqCategoryRequest;
import com.petkpetk.admin.dto.request.FaqRequest;
import com.petkpetk.admin.entity.FaqCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaqCategoryDto {

	private Long id;
	private String name;
	private String description;
	private LocalDateTime createdAt;

	public static FaqCategoryDto from(FaqCategoryRequest faqCategoryRequest) {
		return EntityAndDtoConverter.convertToDto(faqCategoryRequest, FaqCategoryDto.class);
	}

	public static FaqCategoryDto from(FaqCategory faqCategory) {
		return EntityAndDtoConverter.convertToDto(faqCategory, FaqCategoryDto.class);
	}
}
