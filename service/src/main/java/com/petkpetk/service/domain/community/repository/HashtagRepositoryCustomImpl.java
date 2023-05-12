package com.petkpetk.service.domain.community.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.petkpetk.service.domain.community.entity.QHashtag;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class HashtagRepositoryCustomImpl implements HashtagRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	public HashtagRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<String> findAllByOrderByHitDesc() {
		QHashtag hashtag = QHashtag.hashtag;

		return queryFactory.select(hashtag.hashtagName).from(hashtag).orderBy(hashtag.hit.desc()).fetch();
	}
}
