package com.petkpetk.admin.dto.response;

import java.util.List;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.QnaDto;
import com.petkpetk.admin.dto.UserAccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaResponse {

	private Long id;
	private String deletedYn;
	private String createdAt;
	private String content;
	private String phoneNumber;
	private String agree;
	private String qnaCategory;
	private String answerStatus;

	public static QnaResponse from(QnaDto dto) {
		return EntityAndDtoConverter.convertToDto(dto, QnaResponse.class);
	}
}
