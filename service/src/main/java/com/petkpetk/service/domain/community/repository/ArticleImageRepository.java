package com.petkpetk.service.domain.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.petkpetk.service.domain.community.entity.ArticleImage;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long>,
	QuerydslPredicateExecutor<ArticleImage> {
	List<ArticleImage> findByArticleIdOrderByIdAsc(Long itemId);
}