package com.petkpetk.admin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.FaqDto;
import com.petkpetk.admin.dto.ShoppingNoticeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {@Index(columnList = "faq_id"), @Index(columnList = "createdAt")})
@Where(clause = "deleted_yn='N'")
@Entity
public class Faq extends AuditingFields{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Faq_id", length = 10)
	private Long id;

	@Column(length = 50)
	private String title;

	@Column(length = 2000)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "faq_category_id")
	@ToString.Exclude
	private FaqCategory faqCategory;


	public static Faq from(FaqDto faqDto) {
		return EntityAndDtoConverter.convertToEntity(faqDto, Faq.class);
	}
}
