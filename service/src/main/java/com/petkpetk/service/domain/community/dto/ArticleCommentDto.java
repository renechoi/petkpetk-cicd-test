package com.petkpetk.service.domain.community.dto;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import com.petkpetk.service.config.converter.EntityAndDtoConverter;
import com.petkpetk.service.domain.community.entity.ArticleComment;
import com.petkpetk.service.domain.user.dto.UserAccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCommentDto {

	private Long id;
	private ArticleDto articleDto;
	private UserAccountDto userAccountDto;
	private String content;
	private Long parentCommentId;
	private LocalDateTime modifiedAt;
	private Set<ArticleCommentDto> childComments = new LinkedHashSet<>();

	public static ArticleCommentDto from(ArticleComment articleComment) {
		ArticleCommentDto articleCommentDto = EntityAndDtoConverter.convertToDto(articleComment, ArticleCommentDto.class);
		articleCommentDto.setArticleDto(ArticleDto.fromEntity(articleComment.getArticle()));
		articleCommentDto.setUserAccountDto(UserAccountDto.fromEntity(articleComment.getUserAccount()));
		return articleCommentDto;
	}

	public ArticleComment toEntity() {
		return new ArticleComment(
			this.getArticleDto().toEntity(),
			this.getUserAccountDto().toEntity(),
			this.getContent(),
			this.getParentCommentId()
		);
	}

}
