package com.petkpetk.admin.dto.response;

import java.util.List;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.UserAccountDto;

import lombok.Data;

@Data
public class UserAccountResponse {

	private Long id;
	private String createdAt;
	private String email;
	private String name;
	private List<String> roles;
	private String businessName;
	private String businessNumber;
	private String oauth2ProviderInfo;

	public static UserAccountResponse from(UserAccountDto dto) {
		return EntityAndDtoConverter.convertToDto(dto, UserAccountResponse.class);
	}
}
