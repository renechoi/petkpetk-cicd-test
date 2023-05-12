package com.petkpetk.service.domain.user.dto;

import java.time.LocalDateTime;

import com.petkpetk.service.config.converter.EntityAndDtoConverter;
import com.petkpetk.service.domain.user.constant.AnswerStatus;
import com.petkpetk.service.domain.user.constant.QnACategory;
import com.petkpetk.service.domain.user.entity.UserAccount;
import com.petkpetk.service.domain.user.entity.UserAsk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAskDto {
	private Long id;
	private UserAccount userAccount;
	private QnACategory qnACategory;
	private AnswerStatus answerStatus;
	private String phoneNumber;
	private String content;
	private String agree;
	private LocalDateTime createdAt;

	public static UserAskDto from(UserAsk userAsk) {
		return EntityAndDtoConverter.convertToDto(userAsk, UserAskDto.class);
	}

}
