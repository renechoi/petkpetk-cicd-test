package com.petkpetk.admin.controller.management;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petkpetk.admin.dto.response.ArticleResponse;
import com.petkpetk.admin.service.management.ArticleManagementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("management/community-articles")
public class ArticleManagementController {

	private final ArticleManagementService articleManagementService;

	@GetMapping()
	public String articles(Model model) {
		model.addAttribute("articles", articleManagementService.getArticles()
			.stream()
			.map(ArticleResponse::from)
			.collect(Collectors.toList()));

		return "main/management/article-management";
	}

	@ResponseBody
	@GetMapping("/{id}")
	public ArticleResponse article(@PathVariable String id) {
		return ArticleResponse.from(articleManagementService.getArticle(id));
	}

	@PostMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		articleManagementService.deleteArticle(id);
		return ResponseEntity.ok("삭제 성공");

	}
}