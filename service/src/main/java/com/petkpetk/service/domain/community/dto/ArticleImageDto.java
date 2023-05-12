package com.petkpetk.service.domain.community.dto;

import com.petkpetk.service.config.converter.EntityAndDtoConverter;
import com.petkpetk.service.domain.community.entity.ArticleImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleImageDto {

	private Long id;
	private String uniqueName;
	private String originalName;
	private String imageUrl;
	private String representativeImageYn = "N";
	private ArticleDto articleDto;

	public static ArticleImageDto from(ArticleImage articleImage) {
		return EntityAndDtoConverter.convertToDto(articleImage, ArticleImageDto.class);
	}
}
