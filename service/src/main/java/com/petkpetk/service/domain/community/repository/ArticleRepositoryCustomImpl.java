package com.petkpetk.service.domain.community.repository;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.petkpetk.service.domain.community.entity.Article;
import com.petkpetk.service.domain.community.entity.QArticle;
import com.petkpetk.service.domain.community.entity.QHashtag;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public ArticleRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Page<Article> findByHashtagNames(Collection<String> hashtagNames, Pageable pageable) {
		QArticle article = QArticle.article;
		QHashtag hashtag = QHashtag.hashtag;

		BooleanBuilder builder = new BooleanBuilder();
		builder.and(article.deletedYn.eq("N"));
		builder.and(hashtag.hashtagName.in(hashtagNames));

		List<Article> articles = queryFactory.selectFrom(article)
			.leftJoin(article.hashtags, hashtag)
			.where(builder)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		return createArticlePage(pageable, articles);
	}

	private PageImpl<Article> createArticlePage(Pageable pageable, List<Article> articles) {
		int start = (int)pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), articles.size());
		return new PageImpl<>(articles.subList(start, end), pageable, articles.size());
	}

}

