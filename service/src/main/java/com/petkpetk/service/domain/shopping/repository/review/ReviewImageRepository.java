package com.petkpetk.service.domain.shopping.repository.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.petkpetk.service.domain.shopping.entity.review.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long>,
	QuerydslPredicateExecutor<ReviewImage> {

	List<ReviewImage> findByReviewIdOrderByIdAsc(Long reviewid);
	ReviewImage findByUniqueName(String originalName);

}

