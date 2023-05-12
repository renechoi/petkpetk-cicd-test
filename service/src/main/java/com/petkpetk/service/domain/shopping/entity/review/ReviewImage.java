package com.petkpetk.service.domain.shopping.entity.review;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.common.AuditingFields;
import com.petkpetk.service.common.PetkpetkImage;
import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "review_image")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@DynamicUpdate
@Where(clause = "deleted_yn='N'")
public class ReviewImage extends AuditingFields implements PetkpetkImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_image_id")
	private Long id;

	private String uniqueName;
	private String originalName;
	private String imageUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ToString.Exclude
	private Review review;

	public ReviewImage(String originalName, String uniqueName) {
		this.uniqueName = uniqueName;
		this.originalName = originalName;
		this.imageUrl = createImageUrl();
	}

	public void mapWith(Review review) {
		if (this.review == null) {
			this.review = review;
		}
	}

	public static ReviewImage from(MultipartFile rawImage) {
		return new ReviewImage(
			rawImage.getOriginalFilename(),
			createUniqueName(rawImage)
		);
	}


	private String createImageUrl() {
		return "/images/item/" + uniqueName;
	}

	private static String createUniqueName(MultipartFile rawImage) {
		return UUID.randomUUID() + extractExtension(rawImage);
	}

	private static String extractExtension(MultipartFile rawImage) {
		return rawImage.getOriginalFilename()
			.substring(rawImage.getOriginalFilename().lastIndexOf("."));
	}

	@PrePersist
	public void anonymousSetup() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserAccountPrincipal userAccountPrincipal = (UserAccountPrincipal)authentication.getPrincipal();

		this.createdBy = userAccountPrincipal.getName();
		this.modifiedBy = userAccountPrincipal.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ReviewImage)) {
			return false;
		}

		ReviewImage reviewImage = (ReviewImage)o;
		return uniqueName.equals(reviewImage.uniqueName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uniqueName);
	}

	
}

