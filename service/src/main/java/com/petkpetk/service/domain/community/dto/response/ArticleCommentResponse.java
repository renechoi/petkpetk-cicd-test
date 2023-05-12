package com.petkpetk.service.domain.community.dto.response;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.petkpetk.service.domain.community.dto.ArticleDto;
import com.petkpetk.service.domain.community.entity.ArticleComment;
import com.petkpetk.service.domain.user.dto.UserAccountDto;

import lombok.Data;

@Data
public class ArticleCommentResponse {
	private Long id;
	private String content;
	private ArticleDto articleDto;
	private UserAccountDto userAccountDto;
	private Long parentCommentId;
	private LocalDateTime createdAt;
	private Set<ArticleComment> childComments = new HashSet<>();

	public ArticleCommentResponse(Long id, String content, ArticleDto articleDto,
		UserAccountDto userAccountDto, Long parentCommentId, LocalDateTime createdAt,
		Set<ArticleComment> childComments) {
		this.id = id;
		this.content = content;
		this.articleDto = articleDto;
		this.userAccountDto = userAccountDto;
		this.parentCommentId = parentCommentId;
		this.createdAt = createdAt;
		this.childComments = childComments;
	}

	public static ArticleCommentResponse from(ArticleComment articleComment) {
		return new ArticleCommentResponse(
			articleComment.getId(),
			articleComment.getContent(),
			ArticleDto.fromEntity(articleComment.getArticle()),
			UserAccountDto.fromEntity(articleComment.getUserAccount()),
			articleComment.getParentCommentId(),
			articleComment.getCreatedAt(),
			articleComment.getChildComments()
		);
	}
}
