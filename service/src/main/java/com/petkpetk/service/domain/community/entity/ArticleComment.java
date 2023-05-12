package com.petkpetk.service.domain.community.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Where;

import com.petkpetk.service.common.AuditingFields;
import com.petkpetk.service.domain.user.entity.UserAccount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Where(clause = "deleted_yn='N'")
@Entity
public class ArticleComment extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_comment_id", length = 10)
	private Long id;

	@ManyToOne
	private Article article;

	@JoinColumn(name = "user_account_id")
	@ManyToOne
	private UserAccount userAccount;

	@Column(nullable = false)
	private String content;

	private Long parentCommentId;

	@ToString.Exclude
	@OrderBy("createdAt ASC")
	@OneToMany(mappedBy = "parentCommentId", cascade = CascadeType.ALL)
	private Set<ArticleComment> childComments = new LinkedHashSet<>();

	public ArticleComment(Article article, UserAccount userAccount, String content, Long parentCommentId) {
		this.article = article;
		this.userAccount = userAccount;
		this.content = content;
		this.parentCommentId = parentCommentId;
	}
}
