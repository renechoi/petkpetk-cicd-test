package com.petkpetk.service.domain.community.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import com.petkpetk.service.config.converter.EntityAndDtoConverter;
import com.petkpetk.service.domain.community.entity.Article;
import com.petkpetk.service.domain.community.entity.Hashtag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HashtagDto {

	private Long id;
	private String hashtagName;
	private Set<Article> articles = new LinkedHashSet<>();
	private Long hit;

	public static HashtagDto from(Hashtag hashtag) {
		return EntityAndDtoConverter.convertToDto(hashtag, HashtagDto.class);
	}

}
