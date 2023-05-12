package com.petkpetk.service.domain.shopping.entity.review;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.petkpetk.service.common.AuditingFields;
import com.petkpetk.service.domain.shopping.dto.review.response.ReviewResponse;
import com.petkpetk.service.domain.shopping.entity.item.Item;
import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.entity.UserAccount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "review")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Where(clause = "deleted_yn='N'")
public class Review extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="item_id")
	private Item item;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	@Column(nullable = false, length = 1500)
	private String content;

	private Long likes = 0L;

	@Column(nullable = false)
	private Double rating;

	@OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<ReviewImage> images;

	private List<ReviewImage> addImages(List<ReviewImage> images) {
		images.forEach(image -> image.mapWith(this));
		return images;
	}

	public void mapImages(List<ReviewImage> images) {
		images.forEach(image -> image.mapWith(this));
	}

	public Review(Item item, UserAccount userAccount, String content, Long likes,
		List<ReviewImage> images, Double rating) {
		this.item = item;
		this.userAccount = userAccount;
		this.content = content;
		this.likes = likes;
		this.images = addImages(images);
		this.rating = rating;
	}

	public static Review of(Item item, UserAccountDto userAccountDto, String content, Long likes, List<ReviewImage> images, Double rating) {
		return new Review(item, userAccountDto.toEntity(), content, likes, images, rating);
	}
}

