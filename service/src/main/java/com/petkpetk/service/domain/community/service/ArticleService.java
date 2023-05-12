package com.petkpetk.service.domain.community.service;

import static com.petkpetk.service.domain.community.constatnt.SearchType.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.service.domain.community.constatnt.CategoryType;
import com.petkpetk.service.domain.community.constatnt.SearchType;
import com.petkpetk.service.domain.community.dto.ArticleDto;
import com.petkpetk.service.domain.community.entity.Article;
import com.petkpetk.service.domain.community.entity.ArticleImage;
import com.petkpetk.service.domain.community.entity.Hashtag;
import com.petkpetk.service.domain.community.exception.ArticleNotFoundException;
import com.petkpetk.service.domain.community.repository.ArticleCommentRepository;
import com.petkpetk.service.domain.community.repository.ArticleRepository;
import com.petkpetk.service.domain.community.repository.HashtagRepository;
import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;
import com.petkpetk.service.domain.user.entity.UserAccount;
import com.petkpetk.service.domain.user.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;
	private final UserAccountRepository userAccountRepository;
	private final ArticleCommentRepository articleCommentRepository;
	private final articleImageService articleImageService;
	private final HashtagRepository hashtagRepository;

	/**
	 * todo: if문 분기에 대한 추후 리팩토링을 검토해볼 필요가 있다.
	 * 현재와 같은 수준에서는 if문을 분기하는 방식을 고수하되, 검색 타입이 늘어나는 경우 SearchType enum 클래스와 함수형 인터페이스를 이용해
	 * 메서드를 던져주는 방식으로 리팩토링을 고려해보자.
	 */
	@Transactional
	public Page<ArticleDto> searchArticles(SearchType searchType, String searchValue, Pageable pageable) {

		if (searchType == TITLE) {
			return articleRepository.findByTitleContaining(searchValue, pageable).map(this::convertToDto);
		}

		if (searchType == CONTENT) {
			return articleRepository.findByContentContaining(searchValue, pageable).map(this::convertToDto);
		}

		if (searchType == NICKNAME) {
			return articleRepository.findByUserAccount_NicknameContaining(searchValue, pageable)
				.map(this::convertToDto);
		}

		if (searchType == HASHTAG) {
			hashtagRepository.findByHashtagName(searchValue).updateHit();
			return articleRepository.findByHashtagNames(Set.of(searchValue), pageable).map(this::convertToDto);
		}

		if (searchType == CATEGORY && !searchValue.isEmpty()) {
			Set<CategoryType> categoryTypes = Arrays.stream(searchValue.split(","))
				.map(value -> CategoryType.valueOf(value.trim()))
				.collect(Collectors.toSet());

			return articleRepository.findByCategoryTypeIn(categoryTypes, pageable).map(this::convertToDto);
		}

		return articleRepository.findAll(pageable).map(this::convertToDto);
	}

	public ArticleDto searchArticle(Long articleId) {
		Article article = articleRepository.findById(articleId).orElseThrow(ArticleNotFoundException::new);
		article.updateHit();
		return convertToDto(article);
	}

	public void saveArticle(ArticleDto articleDto) {
		UserAccount userAccount = userAccountRepository.findByEmail(articleDto.getUserAccountDto().getEmail())
			.orElseThrow(ArticleNotFoundException::new);

		List<ArticleImage> images = articleImageService.convertToImages(articleDto.getRawImages());

		Set<Hashtag> hashtags = extractHashtags(articleDto.getRawHashtags()).stream()
			.map(hashtag -> hashtagRepository.findOptionalByHashtagName(hashtag.getHashtagName()).orElse(hashtag))
			.collect(Collectors.toUnmodifiableSet());

		articleRepository.save(articleDto.toEntity(userAccount, images, hashtags));
		articleImageService.uploadImages(articleDto, images);
	}

	public void deleteArticle(Long articleId) {
		Article article = articleRepository.getReferenceById(articleId);
		article.setDeletedYn("Y");
		articleRepository.flush();

		deleteHashtagsByArticle(article);
		articleImageService.deleteImagesByArticle(articleId);
	}

	public Long getArticleLastId() {
		return articleRepository.findTopByOrderByIdDesc().getId();
	}

	public List<String> getHashtags() {
		return hashtagRepository.findAllByOrderByHitDesc();
	}

	public int getArticleTotalCount() {
		return articleRepository.findAll().size();
	}

	/**
	 * rawHashtags 의 예시는 다음과 같다.
	 * "#123 #12345 #hashtag #example #해시태그는 #이렇게 #구성되어 #있다"
	 * <p>
	 * 해시태그를 추출하기 위한 정규식 (?<=\\s|^)#[\\p{L}0-9_]+는 다음과 같은 구성으로 이루어져 있다.
	 * <p>
	 * (?<=\\s|^) : 전방탐색(lookbehind)을 사용하여 공백 또는 문자열의 시작 부분(^)이 해시태그(#) 앞에 있는 경우에만 해시태그를 추출한다.
	 * # : 해시태그 기호(#)를 나타낸다.
	 * [\\p{L}0-9_]+ : 해시태그 이름으로 사용될 수 있는 문자열을 나타낸다.
	 * [\\p{L}0-9_] : 유니코드 문자(영어, 한글 등), 숫자, 언더스코어(_) 중 하나를 나타낸다.
	 * + : 1개 이상의 문자열이 나타날 수 있다.
	 */
	public LinkedHashSet<Hashtag> extractHashtags(String rawHashtags) {
		String hashtagRegex = "(?<=\\s|^)#[\\p{L}0-9_]+";

		return Pattern.compile(hashtagRegex)
			.matcher(rawHashtags)
			.results()
			.map(matchResult -> matchResult.group().substring(1))
			.map(Hashtag::of)
			.collect(Collectors.toCollection(LinkedHashSet::new));
	}

	public List<ArticleDto> getUserArticle(UserAccountPrincipal userAccountPrincipal) {
		List<ArticleDto> articleDtoList = new ArrayList<>();
		List<Article> articleList =articleRepository.findAllByUserAccountIdOrderByIdDesc(userAccountPrincipal.getId());
		articleList.forEach(article -> articleDtoList.add(ArticleDto.fromEntity(article)));
		articleDtoList.forEach(article -> article.setCommentCount(articleCommentRepository.findCountArticleId(article.getId())));
		return articleDtoList;
	}

	private void deleteHashtagsByArticle(Article article) {
		Set<Long> hashtagIds = article.getHashtags()
			.stream()
			.map(Hashtag::getId)
			.collect(Collectors.toUnmodifiableSet());
		hashtagIds.forEach(this::deleteHashtagByArticle);
	}

	private void deleteHashtagByArticle(Long hashtagId) {
		Hashtag hashtag = hashtagRepository.getReferenceById(hashtagId);
		if (hashtag.getArticles().stream().allMatch(this::isArticleDeleted)) {
			hashtagRepository.delete(hashtag);
		}
	}

	private ArticleDto convertToDto(Article article) {
		return ArticleDto.from(article, articleImageService.convertToRawImages(article.getArticleImages()));
	}

	private boolean isArticleDeleted(Article article) {
		return articleRepository.getReferenceById(article.getId()).getDeletedYn().equals("Y");
	}


}
