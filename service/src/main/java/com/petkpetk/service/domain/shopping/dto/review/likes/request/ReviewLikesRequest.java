package com.petkpetk.service.domain.shopping.dto.review.likes.request;

import com.petkpetk.service.domain.shopping.entity.review.Review;
import com.petkpetk.service.domain.shopping.entity.review.likes.ReviewLikes;
import com.petkpetk.service.domain.user.entity.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data()
@NoArgsConstructor
@AllArgsConstructor
public class ReviewLikesRequest {

	private Long id;
	private UserAccount userAccount;
	private Review review;

	public ReviewLikesRequest(UserAccount userAccount, Review review) {
		this.id = id;
		this.userAccount = userAccount;
		this.review = review;
	}

	public ReviewLikes toEntity(){
		return ReviewLikes.of(
			this.userAccount,
			this.review
		);
	}

	public static ReviewLikesRequest of(UserAccount userAccount, Review review) {
		return new ReviewLikesRequest(userAccount, review);
	}

}
