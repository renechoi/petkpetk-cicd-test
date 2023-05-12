package com.petkpetk.service.domain.community.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.petkpetk.service.domain.community.entity.Article;
import com.petkpetk.service.domain.community.entity.Hashtag;

@DisplayName("Article Repository 테스트")
@ActiveProfiles("test")
@TestPropertySource(properties = {
	"spring.sql.init.mode=never",
	"spring.jpa.defer-datasource-initialization=false"
})

@DataJpaTest
public class ArticleRepositoryTest {

	@Autowired
	private ArticleRepository articleRepository;

	@DisplayName("select 테스트")
	@Test
	public void should_selectSuccess() {
		// given
		Article article = createFixtureArticle("Test Article Title", "Test Article Content", 0L);
		article = articleRepository.save(article);

		// when
		Article foundArticle = articleRepository.findById(article.getId()).orElse(null);

		// then
		assertNotNull(foundArticle);
		assertEquals(article.getTitle(), foundArticle.getTitle());
	}

	@DisplayName("insert 테스트")
	@Test
	public void should_insertSuccess() {
		// given
		long previousCount = articleRepository.count();
		Article article = createFixtureArticle("title", "content", 0L);
		article.setHashtags(Set.of(Hashtag.of("hashtag")));

		// when
		articleRepository.save(article);

		// then
		Assertions.assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
	}

	@DisplayName("update 테스트")
	@Test
	public void should_updateSuccess() {
		// given
		Article article = createFixtureArticle("Test Title", "Test Content", 0L);
		article = articleRepository.save(article);

		// when
		article.setTitle("Updated Title");
		article.setContent("Updated Content");
		articleRepository.save(article);
		Article updatedArticle = articleRepository.findById(article.getId()).orElse(null);

		// then
		assertNotNull(updatedArticle);
		assertEquals(article.getTitle(), updatedArticle.getTitle());
		assertEquals(article.getContent(), updatedArticle.getContent());
	}

	@DisplayName("delete 테스트")
	@Test
	public void should_deleteSuccess() {
		// given
		Article article = createFixtureArticle("Test Title", "Test Content", 0L);
		article = articleRepository.save(article);

		// when
		articleRepository.delete(article);
		Article deletedArticle = articleRepository.findById(article.getId()).orElse(null);

		// then
		assertNull(deletedArticle);
	}

	private static Article createFixtureArticle(String title, String content, Long hit) {
		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);
		article.setHit(hit);
		return article;
	}
}
