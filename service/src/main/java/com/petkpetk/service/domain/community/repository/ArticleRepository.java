package com.petkpetk.service.domain.community.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.petkpetk.service.domain.community.constatnt.CategoryType;
import com.petkpetk.service.domain.community.entity.Article;

public interface ArticleRepository
	extends JpaRepository<Article, Long>, ArticleRepositoryCustom, QuerydslPredicateExecutor<Article> {

	Page<Article> findByTitleContaining(String title, Pageable pageable);

	Page<Article> findByContentContaining(String content, Pageable pageable);

	Page<Article> findByCategoryTypeIn(Set<CategoryType> categoryTypes, Pageable pageable);

	Page<Article> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);

	Article findTopByOrderByIdDesc();

	List<Article> findAllByUserAccountIdOrderByIdDesc(Long userAccountId);
}
