package com.petkpetk.service.domain.community.entity;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.petkpetk.service.common.AuditingFields;
import com.petkpetk.service.domain.community.constatnt.CategoryType;
import com.petkpetk.service.domain.user.entity.UserAccount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Table(indexes = {@Index(columnList = "title"), @Index(columnList = "createdAt"), @Index(columnList = "createdBy")})
@Where(clause = "deleted_yn='N'")
@Entity
public class Article extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id", length = 10)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	@Column(length = 30)
	private String title;
	@Column(length = 5000)
	private String content;
	@Column(length = 10)
	private Long hit;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id")
	@ToString.Exclude
	private Set<ArticleLikes> likes;

	@ToString.Exclude
	@JoinTable(name = "article_hashtag", joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Hashtag> hashtags = new LinkedHashSet<>();

	@ToString.Exclude
	@OrderBy("createdAt DESC")
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
	private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<ArticleImage> articleImages;

	@Enumerated(EnumType.STRING)
	private CategoryType categoryType;

	public void updateHit() {
		this.hit += 1;
	}

	public void addImages(List<ArticleImage> images) {
		if (images.isEmpty()) {
			this.articleImages = null;
			return;
		}
		setRepresentativeImage(images).forEach(image -> image.mapWith(this));
		this.articleImages = images;
	}

	public void addHashtags(Set<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}

	private List<ArticleImage> setRepresentativeImage(List<ArticleImage> images) {
		images.get(0).setRepresentativeImageYn("Y");
		return images;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Article))
			return false;
		Article article = (Article)o;
		return id.equals(article.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
