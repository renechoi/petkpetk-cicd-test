package com.petkpetk.service.domain.user.entity;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.common.PetkpetkImage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class ProfileImage implements PetkpetkImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_image_id")
	private Long id;

	private String uniqueName;

	private String originalName;

	private String imageUrl;

	private String representativeImageYn = "N";

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_account_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ToString.Exclude
	private UserAccount userAccount;

	public ProfileImage() {
		this.uniqueName = createUniqueName();
	}

	private ProfileImage(String imageUrl) {
		this.uniqueName = createUniqueName();
		this.imageUrl = imageUrl;
	}

	public ProfileImage(String originalName, String uniqueName) {
		this.originalName = originalName;
		this.uniqueName = uniqueName;
		this.imageUrl = createImageUrl();
	}

	public static ProfileImage of(String imageUrl) {
		return new ProfileImage(imageUrl);
	}

	public static ProfileImage of(String OriginalName, String uniqueName) {
		return new ProfileImage(OriginalName, uniqueName);
	}

	public void mapWith(UserAccount userAccount) {
		if (this.userAccount == null) {
			this.userAccount = userAccount;
		}
	}

	public static ProfileImage from(MultipartFile rawImage) {
		return new ProfileImage(
			rawImage.getOriginalFilename(),
			createUniqueName(rawImage)
		);
	}

	private String createImageUrl() {
		return "/images/item/" + uniqueName;
	}

	private static String createUniqueName() {
		return UUID.randomUUID().toString();
	}

	private static String createUniqueName(MultipartFile rawImage) {
		return UUID.randomUUID() + extractExtension(rawImage);
	}

	private static String extractExtension(MultipartFile rawImage) {
		return rawImage.getOriginalFilename().substring(rawImage.getOriginalFilename().lastIndexOf("."));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ProfileImage))
			return false;
		ProfileImage that = (ProfileImage)o;
		return uniqueName.equals(that.uniqueName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uniqueName);
	}
}
