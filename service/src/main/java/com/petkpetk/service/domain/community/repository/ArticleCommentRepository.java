package com.petkpetk.service.domain.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.petkpetk.service.domain.community.entity.ArticleComment;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
	@Query("select count(c) from ArticleComment c where c.article.id = ?1")
	Long findCountArticleId(Long articleId);

	List<ArticleComment> findAllByArticleId(Long articleId);

	List<ArticleComment> findAllByUserAccountIdOrderByIdDesc(Long userAccountId);
}
