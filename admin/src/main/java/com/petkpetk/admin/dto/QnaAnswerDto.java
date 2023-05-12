package com.petkpetk.admin.dto;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.request.QnaAnswerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaAnswerDto {
	private String answer;
	private String userAskId;

	public static QnaAnswerDto from(QnaAnswerRequest qnaAnswerRequest) {
		return EntityAndDtoConverter.convertToDto(qnaAnswerRequest, QnaAnswerDto.class);
	}
}
