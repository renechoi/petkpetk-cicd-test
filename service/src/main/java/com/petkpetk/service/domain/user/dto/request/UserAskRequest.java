package com.petkpetk.service.domain.user.dto.request;

import com.petkpetk.service.config.converter.EntityAndDtoConverter;
import com.petkpetk.service.domain.user.constant.QnACategory;
import com.petkpetk.service.domain.user.entity.UserAccount;
import com.petkpetk.service.domain.user.entity.UserAsk;

import lombok.Data;

@Data
public class UserAskRequest {

	private UserAccount userAccount;
	private QnACategory qnACategory;
	private String phoneNumber;
	private String content;
	private String agree;

	public UserAsk toEntity() {
		return EntityAndDtoConverter.convertToEntity(this, UserAsk.class);
	}
}
