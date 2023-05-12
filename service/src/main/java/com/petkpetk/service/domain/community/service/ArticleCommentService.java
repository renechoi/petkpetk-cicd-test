package com.petkpetk.service.domain.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.service.domain.community.dto.ArticleCommentDto;
import com.petkpetk.service.domain.community.dto.ArticleDto;
import com.petkpetk.service.domain.community.dto.request.ArticleCommentPostRequest;
import com.petkpetk.service.domain.community.dto.response.ArticleCommentResponse;
import com.petkpetk.service.domain.community.entity.ArticleComment;
import com.petkpetk.service.domain.community.repository.ArticleCommentRepository;
import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;
import com.petkpetk.service.domain.user.service.UserAccountService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommentService {

	private final ArticleCommentRepository articleCommentRepository;
	private final UserAccountService userAccountService;
	private final ArticleService articleService;

	public void save(ArticleCommentPostRequest articleCommentPostRequest) {

		ArticleCommentDto articleCommentDto = new ArticleCommentDto();
		UserAccountDto userAccountDto = userAccountService.searchUserDto(articleCommentPostRequest.getUserEmail());
		ArticleDto articleDto =articleService.searchArticle(articleCommentPostRequest.getArticleId());
		if (articleCommentPostRequest.getParentCommentId()!=null) {
			articleCommentDto.setParentCommentId(articleCommentPostRequest.getParentCommentId());
		}
		articleCommentDto.setArticleDto(articleDto);
		articleCommentDto.setUserAccountDto(userAccountDto);
		articleCommentDto.setContent(articleCommentPostRequest.getContent());

		articleCommentRepository.save(articleCommentDto.toEntity());
	}

	public List<ArticleCommentResponse> findCommentList(Long articleId) {
		List<ArticleComment> articleComments = articleCommentRepository.findAllByArticleId(articleId);
		List<ArticleCommentResponse> articleCommentResponses = new ArrayList<>();
		for (ArticleComment articleComment : articleComments) {
			articleCommentResponses.add(ArticleCommentResponse.from(articleComment));
		}

		return articleCommentResponses;
	}

	public void deleteComment(Long commentId) {
		ArticleComment articleComment = articleCommentRepository.findById(commentId).orElseThrow();
		articleComment.setDeletedYn("Y");

		articleCommentRepository.flush();
	}
	public void modifyComment(Long commentId, ArticleCommentPostRequest articleCommentPostRequest) {
		ArticleComment articleComment = articleCommentRepository.findById(commentId).orElseThrow();
		articleComment.setContent(articleCommentPostRequest.getContent());

		articleCommentRepository.flush();
	}

	public List<ArticleCommentDto> getUserComment(UserAccountPrincipal userAccountPrincipal) {
		List<ArticleCommentDto> articleCommentDtoList = new ArrayList<>();
		List<ArticleComment> articleCommentList = articleCommentRepository.findAllByUserAccountIdOrderByIdDesc(userAccountPrincipal.getId());
		articleCommentList.forEach(articleComment -> articleCommentDtoList.add(ArticleCommentDto.from(articleComment)));
		return articleCommentDtoList;
	}
}
