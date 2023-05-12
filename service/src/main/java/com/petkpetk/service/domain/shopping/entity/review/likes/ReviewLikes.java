package com.petkpetk.service.domain.shopping.entity.review.likes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.petkpetk.service.domain.shopping.dto.review.likes.request.ReviewLikesRequest;
import com.petkpetk.service.domain.shopping.entity.review.Review;
import com.petkpetk.service.domain.user.entity.UserAccount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "review_likes")
public class ReviewLikes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "likes_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_id")
	private Review review;

	public ReviewLikes(UserAccount userAccount, Review review) {
		this.userAccount = userAccount;
		this.review = review;
	}


	public static ReviewLikes of(UserAccount userAccount, Review review){
		return new ReviewLikes(userAccount, review);
	}

	public static ReviewLikes of(ReviewLikesRequest reviewLikesRequest) {
		return ReviewLikes.of(reviewLikesRequest.getUserAccount(), reviewLikesRequest.getReview());
	}
}
