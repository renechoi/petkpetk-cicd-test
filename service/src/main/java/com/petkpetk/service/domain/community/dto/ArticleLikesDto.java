package com.petkpetk.service.domain.community.dto;

import com.petkpetk.service.domain.user.dto.UserAccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleLikesDto {

	private Long id;
	private UserAccountDto userAccountDto;
	private ArticleDto articleDto;
}
