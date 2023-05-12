package com.petkpetk.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.QnaAnswerDto;

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
@Table(indexes = {@Index(columnList = "qna_answer_id"), @Index(columnList = "createdAt")})
@Where(clause = "deleted_yn='N'")
@Entity
public class QnaAnswer extends AuditingFields{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "qna_answer_id", length = 10)
	private Long id;

	@Column(length = 2000)
	private String answer;

	@Column(length = 10)
	private String userAskId;

	public static QnaAnswer from(QnaAnswerDto qnaAnswerDto) {
		return EntityAndDtoConverter.convertToEntity(qnaAnswerDto, QnaAnswer.class);
	}
}
