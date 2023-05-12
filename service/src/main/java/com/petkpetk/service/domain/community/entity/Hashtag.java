package com.petkpetk.service.domain.community.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.petkpetk.service.common.AuditingFields;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Table(indexes = {
	@Index(columnList = "hashtagName", unique = true),
	@Index(columnList = "createdAt"),
	@Index(columnList = "createdBy")
})
@NoArgsConstructor
@Where(clause = "deleted_yn='N'")
@Entity
public class Hashtag extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hashtag_id", length = 10)
	private Long id;
	private String hashtagName;
	private Long hit = 0L;

	@ToString.Exclude
	@ManyToMany(mappedBy = "hashtags")
	private Set<Article> articles = new LinkedHashSet<>();

	public Hashtag(String hashtagName) {
		this.hashtagName = hashtagName;
	}

	public Hashtag(String hashtagName, Article article) {
		this.hashtagName = hashtagName;
		this.articles.add(article);
	}

	public static Hashtag of(String hashtagName) {
		return new Hashtag(hashtagName);
	}

	public static Hashtag of(String hashtagName, Article article) {
		return new Hashtag(hashtagName, article);
	}

	public void updateHit() {
		this.hit += 1;
	}
}
