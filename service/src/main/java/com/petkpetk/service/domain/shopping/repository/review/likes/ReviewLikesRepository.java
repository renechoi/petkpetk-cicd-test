package com.petkpetk.service.domain.shopping.repository.review.likes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petkpetk.service.domain.shopping.entity.review.Review;
import com.petkpetk.service.domain.shopping.entity.review.likes.ReviewLikes;
import com.petkpetk.service.domain.user.entity.UserAccount;

public interface ReviewLikesRepository extends JpaRepository<ReviewLikes, Long> {
	ReviewLikes findByUserAccountAndReview(UserAccount userAccount, Review review);
	List<ReviewLikes> findAllByUserAccount(UserAccount userAccount);
	void deleteAllByReviewId(Long reviewId);
}
