package com.petkpetk.admin.dto;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaDto {

	private Long id;
	private String deletedYn;
	private String createdAt;
	private String content;
	private String phoneNumber;
	private String agree;
	private String qnaCategory;
	private String answerStatus;
}
