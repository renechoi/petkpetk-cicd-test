package com.petkpetk.admin.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.FaqCategoryDto;

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
@Table(indexes = {@Index(columnList = "faq_category_id"), @Index(columnList = "createdAt")})
@Where(clause = "deleted_yn='N'")
@Entity
public class FaqCategory extends AuditingFields {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Faq_category_id", length = 10)
	private Long id;

	@Column(length = 50)
	private String name;

	@Column(length = 2000)
	private String description;

	@OneToMany(mappedBy = "faqCategory", fetch = FetchType.LAZY)
	@ToString.Exclude
	private List<Faq> faqs;

	public static FaqCategory from(FaqCategoryDto faqCategoryDto) {
		return EntityAndDtoConverter.convertToEntity(faqCategoryDto, FaqCategory.class);
	}
}
