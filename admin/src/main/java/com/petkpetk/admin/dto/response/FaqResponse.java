package com.petkpetk.admin.dto.response;

import java.time.LocalDateTime;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.FaqCategoryDto;
import com.petkpetk.admin.entity.Faq;
import com.petkpetk.admin.entity.ShoppingNotice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaqResponse {

	private Long id;
	private String title;
	private String content;
	private FaqCategoryDto faqCategory;
	private LocalDateTime createdAt;

	public static FaqResponse from(Faq faq) {
		FaqResponse faqResponse = EntityAndDtoConverter.convertToDto(faq, FaqResponse.class);
		faqResponse.setFaqCategory(FaqCategoryDto.from(faq.getFaqCategory()));
		return faqResponse;
	}
}
