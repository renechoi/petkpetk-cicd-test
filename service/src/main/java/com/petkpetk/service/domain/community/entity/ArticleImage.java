package com.petkpetk.service.domain.community.entity;

import static com.petkpetk.service.config.properties.ServerProperty.*;

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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;
import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.common.AuditingFields;
import com.petkpetk.service.common.PetkpetkImage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Where(clause = "deleted_yn='N'")
@Entity
public class ArticleImage extends AuditingFields implements PetkpetkImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_image_id")
	private Long id;

	private String uniqueName;

	private String originalName;

	private String imageUrl;

	private String representativeImageYn = "N";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ToString.Exclude
	private Article article;

	public ArticleImage(String originalName, String uniqueName) {
		this.originalName = originalName;
		this.uniqueName = uniqueName;
		this.imageUrl = createImageUrl();
	}

	public void mapWith(Article article) {
		if (this.article == null) {
			this.article = article;
		}
	}

	public static ArticleImage from(MultipartFile rawImage) {
		return new ArticleImage(rawImage.getOriginalFilename(), createUniqueName(rawImage));
	}

	public static ArticleImage asRepresentative(MultipartFile rawImage) {
		ArticleImage articleImage = ArticleImage.from(rawImage);
		articleImage.setRepresentativeImageYn("Y");
		return articleImage;
	}

	private String createImageUrl() {
		return IMAGE_SERVER_LOCATION.getServerLocation() + uniqueName;
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
		if (!(o instanceof ArticleImage))
			return false;
		ArticleImage articleImage = (ArticleImage)o;
		return uniqueName.equals(articleImage.uniqueName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uniqueName);
	}
}
