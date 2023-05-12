package com.petkpetk.service.domain.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petkpetk.service.domain.community.dto.request.ArticleCommentPostRequest;
import com.petkpetk.service.domain.community.service.ArticleCommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community/comment")
public class ArticleCommentController {

	private final ArticleCommentService articleCommentService;

	@PostMapping("/post")
	public String postComment(ArticleCommentPostRequest articleCommentPostRequest) {

		articleCommentService.save(articleCommentPostRequest);
		return "redirect:/community/articles/" + articleCommentPostRequest.getArticleId();
	}

	@PostMapping("/delete/{articleId}/{commentId}")
	public String deleteComment(@PathVariable("articleId") Long articleId, @PathVariable("commentId") Long commentId) {
		articleCommentService.deleteComment(commentId);
		return "redirect:/community/articles/"+articleId;
	}

	@PostMapping("/modify/{articleId}/{commentId}")
	public String modifyComment(@PathVariable("articleId") Long articleId, @PathVariable("commentId") Long commentId, ArticleCommentPostRequest articleCommentPostRequest) {
		articleCommentService.modifyComment(commentId, articleCommentPostRequest);
		return "redirect:/community/articles/"+articleId;
	}
}
