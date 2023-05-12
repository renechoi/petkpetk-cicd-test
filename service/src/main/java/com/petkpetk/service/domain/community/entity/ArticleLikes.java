package com.petkpetk.service.domain.community.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

import com.petkpetk.service.common.AuditingFields;
import com.petkpetk.service.domain.user.entity.UserAccount;

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
public class ArticleLikes extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "likes_id",  length = 10)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_account_id")
	@ToString.Exclude
	private UserAccount userAccount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id")
	@ToString.Exclude
	private Article article;

	public ArticleLikes(UserAccount userAccount, Article article) {
		this.userAccount = userAccount;
		this.article = article;
	}

	public static ArticleLikes of(UserAccount userAccount, Article article){
		return new ArticleLikes(userAccount, article);
	}
}
