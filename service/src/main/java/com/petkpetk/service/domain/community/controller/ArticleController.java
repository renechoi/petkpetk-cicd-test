package com.petkpetk.service.domain.community.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petkpetk.service.domain.community.constatnt.SearchType;
import com.petkpetk.service.domain.community.dto.ArticleDto;
import com.petkpetk.service.domain.community.dto.request.ArticleCommentPostRequest;
import com.petkpetk.service.domain.community.dto.request.ArticlePostRequest;
import com.petkpetk.service.domain.community.dto.response.ArticleCommentResponse;
import com.petkpetk.service.domain.community.dto.response.ArticleResponse;
import com.petkpetk.service.domain.community.service.ArticleCommentService;
import com.petkpetk.service.domain.community.service.ArticleService;
import com.petkpetk.service.domain.community.service.PaginationService;
import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community/articles")
public class ArticleController {

	private final ArticleService articleService;
	private final ArticleCommentService articleCommentService;
	private final PaginationService paginationService;

	/**
	 * todo : 현재 검색과 정렬을 하는 과정에서 쿼리 파라미터가 그대로 노출된다는 문제가 있다. 추후 리팩토링시 노출하지 않는 방법을 찾아본다.
	 * e.g. articles?page=0&sort=&searchType=HASHTAG&searchValue=hashtag1
	 */
	@GetMapping
	public String articles(
		@RequestParam(required = false, name = "searchType") SearchType searchType,
		@RequestParam(required = false) String searchValue,
		@PageableDefault(size = 12) Pageable pageable, Model model,
		@RequestParam(required = false, defaultValue = "createdAt") String sort) {

		pageable = getPageable(pageable, sort);
		Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, pageable)
			.map(ArticleResponse::from);
		List<Integer> pageBars = paginationService.getPageBars(pageable.getPageNumber(), articles.getTotalPages());
		List<String> hashtags = articleService.getHashtags();
		int totalCount = articleService.getArticleTotalCount();

		model.addAttribute("hashtags", hashtags);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("articles", articles);
		model.addAttribute("pageBars", pageBars);
		model.addAttribute("searchTypes", SearchType.values());
		model.addAttribute("searchTypeHashtag", SearchType.HASHTAG);

		return "/community/article/articles";
	}

	@GetMapping("/{articleId}")
	public String article(@PathVariable Long articleId, Model model) {
		ArticleResponse article = ArticleResponse.from(articleService.searchArticle(articleId));
		List<ArticleCommentResponse> articleCommentResponse = articleCommentService.findCommentList(articleId);

		model.addAttribute("article", article);
		model.addAttribute("commentList", articleCommentResponse);
		model.addAttribute("articleCommentPostRequest", new ArticleCommentPostRequest());
		model.addAttribute("lastArticleId", articleService.getArticleLastId());
		model.addAttribute("searchTypeHashtag", SearchType.HASHTAG);

		return "/community/article/detail";
	}

	@GetMapping("/post")
	public String postArticle(Model model) {
		model.addAttribute("article", new ArticlePostRequest());
		return "/community/article/post";
	}

	@PostMapping("/post")
	public String postArticle(@Valid ArticlePostRequest articlePostRequest,
		@AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal) {

		articleService.saveArticle(ArticleDto.from(articlePostRequest, UserAccountDto.from(userAccountPrincipal)));

		return "redirect:/community/articles";
	}

	@PostMapping("/{articleId}/delete")
	public String deleteArticle(@PathVariable Long articleId) {
		articleService.deleteArticle(articleId);

		return "redirect:/articles";
	}

	private Pageable getPageable(Pageable pageable, String sort) {
		if (sort.equals("hit")) {
			pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("hit").descending());
		} else {
			pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("createdAt").descending());
		}
		return pageable;
	}
}
