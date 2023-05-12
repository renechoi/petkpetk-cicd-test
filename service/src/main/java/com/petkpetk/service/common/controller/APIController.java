package com.petkpetk.service.common.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petkpetk.service.domain.community.constatnt.SearchType;
import com.petkpetk.service.domain.community.dto.response.ArticleResponse;
import com.petkpetk.service.domain.community.service.ArticleService;
import com.petkpetk.service.domain.shopping.dto.item.ItemSearchDto;
import com.petkpetk.service.domain.shopping.dto.item.MainItemDto;
import com.petkpetk.service.domain.shopping.service.item.ItemService;
import com.petkpetk.service.domain.shopping.service.review.ReviewService;
import com.petkpetk.service.domain.shopping.service.review.likes.ReviewLikesService;
import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;
import com.petkpetk.service.domain.user.service.UserAccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class APIController {

	private final UserAccountService userAccountService;
	private final ItemService itemService;
	private final ReviewService reviewService;
	private final ReviewLikesService reviewLikesService;
	private final ArticleService articleService;

	@RequestMapping(value = "/review/like", method = RequestMethod.POST)
	public Boolean like(Long num, Long reviewId, Long likeNum, String userEmail) {

		if (num == 1) {
			reviewService.plusLike(reviewId, likeNum);
			reviewLikesService.plusLike(reviewId, userEmail);
			return true;
		} else if (num == -1) {
			reviewService.minusLike(reviewId);
			reviewLikesService.minusLike(reviewId, userEmail);
			return true;
		} else {
			return false;
		}

	}

	@RequestMapping("/items")
	@ResponseBody
	public Map<String, Object> getItems(ItemSearchDto itemSearchDto, Optional<Integer> page) {
		Map<String, Object> result = new HashMap<>();

		PageRequest pageRequest = PageRequest.of(page.orElse(0), 12);
		Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageRequest);

		Long itemCount = itemService.getItemCount();

		result.put("result", items.getContent());
		result.put("totalItemCount", itemCount);

		return result;
	}

	@RequestMapping("/articles")
	@ResponseBody
	public Map<String, Object> getArticles(
		@RequestParam(required = false, name = "searchType") SearchType searchType,
		@RequestParam(required = false) String searchValue,
		@PageableDefault(size = 12) Pageable pageable, Model model,
		@RequestParam(required = false, defaultValue = "createdAt") String sort) {

		Map<String, Object> result = new HashMap<>();

		if (sort.equals("hit")) {
			pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("hit").descending());
		} else {
			pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("createdAt").descending());
		}

		Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue,
				pageable)
			.map(ArticleResponse::from);

		int totalCount = articleService.getArticleTotalCount();

		result.put("article", articles.getContent());
		result.put("totalCount", totalCount);

		return result;
	}

	@RequestMapping("/checkPass")
	@ResponseBody
	public boolean checkPass(String password, String email) {
		return userAccountService.isPasswordSame(password, email);
	}

	@RequestMapping("/checkEmail")
	@ResponseBody
	public boolean checkEmail(String email) {
		return userAccountService.isEmailDuplicate(email);
	}

	@RequestMapping("/checkNickName")
	@ResponseBody
	public boolean checkNickname(String nickname, String email) {
		return userAccountService.isNicknameDuplicate(nickname, email);
	}

	@RequestMapping("/setUserPrincipalProfile")
	public boolean setUserPrincipalProfile(@AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal) {
		 userAccountPrincipal.setProfileImage(userAccountService.getUserProfile(userAccountPrincipal));
		 return true;
	}

}
