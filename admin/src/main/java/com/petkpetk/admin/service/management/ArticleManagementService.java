package com.petkpetk.admin.service.management;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.petkpetk.admin.config.properties.ProjectProperties;
import com.petkpetk.admin.dto.ArticleDto;
import com.petkpetk.admin.dto.UserAccountDto;
import com.petkpetk.admin.dto.response.ArticleApiResponse;
import com.petkpetk.admin.dto.response.UserAccountApiResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleManagementService {
	private final RestTemplate restTemplate;
	private final ProjectProperties projectProperties;

	public List<ArticleDto> getArticles() {
		URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.getPetkPetk().getUrl() + "/api/articles")
			.queryParam("size", 10000)
			.build()
			.toUri();

		return Optional.ofNullable(restTemplate.getForObject(uri, ArticleApiResponse.class))
			.orElseGet(ArticleApiResponse::empty).getArticle();
	}

	public ArticleDto getArticle(String id) {
		URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.getPetkPetk().getUrl() + "/api/articles/" + id)
			.build()
			.toUri();

		return Optional.ofNullable(
				restTemplate.getForObject(uri, ArticleDto.class))
			.orElseThrow(() -> new NoSuchElementException("게시글이 없습니다 - articleId: " + id));
	}

	public void deleteArticle(String articleId) {
		URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.getPetkPetk().getUrl() + "/api/articles/" + articleId)
			.build()
			.toUri();
		restTemplate.delete(uri);
	}

}

