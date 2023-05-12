package com.petkpetk.admin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
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
@Table(indexes = {@Index(columnList = "shopping_notice_id"), @Index(columnList = "createdAt")})
@Where(clause = "deleted_yn='N'")
@Entity
public class ShoppingNotice extends AuditingFields{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shopping_notice_id", length = 30)
	private Long id;

	@Column(length = 50)
	private String title;

	@Column(length = 2000)
	private String content;

	@OneToMany(mappedBy = "shoppingNotice", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<ShoppingNoticeImage> images;

	public static ShoppingNotice from(ShoppingNoticeDto shoppingNoticeDto) {
		return EntityAndDtoConverter.convertToEntity(shoppingNoticeDto, ShoppingNotice.class);
	}
}
