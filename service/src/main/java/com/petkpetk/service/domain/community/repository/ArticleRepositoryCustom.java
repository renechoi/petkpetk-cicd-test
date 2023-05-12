package com.petkpetk.service.domain.community.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.petkpetk.service.domain.community.entity.Article;

public interface ArticleRepositoryCustom {

	Page<Article> findByHashtagNames(Collection<String> hashtagNames, Pageable pageable);
}
