package com.petkpetk.service.domain.community.service;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.domain.community.constatnt.CategoryType;
import com.petkpetk.service.config.file.ImageLocalRepository;
import com.petkpetk.service.domain.community.dto.ArticleDto;
import com.petkpetk.service.domain.community.entity.Article;
import com.petkpetk.service.domain.community.entity.ArticleImage;
import com.petkpetk.service.domain.community.fixture.ArticleEntityFixture;
import com.petkpetk.service.domain.community.repository.ArticleImageRepository;
import com.petkpetk.service.domain.community.repository.ArticleRepository;
import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.entity.UserAccount;
import com.petkpetk.service.domain.user.repository.UserAccountRepository;

@DisplayName("ArticleService 테스트")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

	@InjectMocks
	private ArticleService articleService;

	@Mock
	private ArticleRepository articleRepository;
	@Mock
	ArticleImageRepository articleImageRepository;

	@Mock
	ImageLocalRepository<ArticleImage> articleImageLocalRepository;

	@Mock
	private UserAccountRepository userAccountRepository;

	@DisplayName("save 테스트")
	@Test
	public void should_SaveSuccess() {
		// given
		UserAccount userAccount = new UserAccount();
		userAccount.setEmail("email@email.com");
		when(userAccountRepository.findByEmail(anyString())).thenReturn(Optional.of(userAccount));

		List<MultipartFile> rawImages = List.of(ArticleEntityFixture.createMockMultipartFile(),
			ArticleEntityFixture.createMockMultipartFile());
		List<ArticleImage> convertedImages = List.of(ArticleImage.from(rawImages.get(0)),
			ArticleImage.from(rawImages.get(1)));

		Article article = ArticleEntityFixture.createArticle(userAccount, CategoryType.CAT);
		article.setArticleImages(convertedImages);
		UserAccountDto userAccountDto = UserAccountDto.fromEntity(userAccount);
		ArticleDto articleDto = ArticleDto.fromEntity(article);
		articleDto.setUserAccountDto(userAccountDto);
		articleDto.setRawImages(rawImages);
		when(articleRepository.save(any(Article.class))).thenReturn(article);

		// when
		articleService.saveArticle(articleDto);

		// then
		verify(userAccountRepository, times(1)).findByEmail(anyString());
		verify(articleRepository, times(1)).save(any(Article.class));
	}

	@DisplayName("delete 테스트")
	@Test
	public void should_DeleteSuccess() {
		Long articleId = 1L;
		Article article = new Article();
		article.setId(articleId);
		when(articleRepository.getReferenceById(articleId)).thenReturn(article);
		when(articleImageRepository.findByArticleIdOrderByIdAsc(anyLong())).thenReturn(List.of(new ArticleImage()));
		doNothing().when(articleImageLocalRepository).deleteFiles(any());

		// when
		articleService.deleteArticle(articleId);

		// then
		verify(articleRepository, times(1)).getReferenceById(articleId);
	}
}









