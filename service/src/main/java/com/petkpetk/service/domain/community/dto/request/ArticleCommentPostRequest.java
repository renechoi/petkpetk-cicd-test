package com.petkpetk.service.domain.community.dto.request;

import lombok.Data;

@Data
public class ArticleCommentPostRequest {
	private String userEmail;
	private Long articleId;
	private String content;
	private Long parentCommentId;
}
