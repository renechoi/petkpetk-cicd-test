package com.petkpetk.service.domain.shopping.service.review.likes;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.service.domain.shopping.dto.review.likes.request.ReviewLikesRequest;
import com.petkpetk.service.domain.shopping.entity.review.Review;

import com.petkpetk.service.domain.shopping.entity.review.likes.ReviewLikes;
import com.petkpetk.service.domain.shopping.repository.review.ReviewRepository;
import com.petkpetk.service.domain.shopping.repository.review.likes.ReviewLikesRepository;
import com.petkpetk.service.domain.user.entity.UserAccount;
import com.petkpetk.service.domain.user.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewLikesService {

	private final ReviewLikesRepository reviewLikesRepository;
	private final UserAccountRepository userAccountRepository;
	private final ReviewRepository reviewRepository;

	public void plusLike(Long reviewId, String userEmail) {
		UserAccount userAccount = userAccountRepository.findByEmail(userEmail).get();
		Review review = reviewRepository.findById(reviewId).get();
		ReviewLikesRequest reviewLikesRequest = ReviewLikesRequest.of(userAccount, review);

		reviewLikesRepository.save(reviewLikesRequest.toEntity());

	}

	public void minusLike(Long reviewId, String userEmail) {
		UserAccount userAccount = userAccountRepository.findByEmail(userEmail).get();
		Review review = reviewRepository.findById(reviewId).get();
		ReviewLikes likes = reviewLikesRepository.findByUserAccountAndReview(userAccount, review);
		reviewLikesRepository.deleteById(likes.getId());

	}

	public HashMap<String, String> findHistoryLikeByUser(Long id) {
		UserAccount userAccount = userAccountRepository.findById(id).get();
		List<ReviewLikes> likes  = reviewLikesRepository.findAllByUserAccount(userAccount);
		HashMap<String, String> hashMap = new HashMap<>();
		for (ReviewLikes like : likes) {
			hashMap.put("review" + like.getReview().getId(), "Y");
		}

		return hashMap;

	}

}
