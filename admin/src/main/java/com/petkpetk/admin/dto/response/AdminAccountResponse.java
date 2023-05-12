package com.petkpetk.admin.dto.response;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import com.petkpetk.admin.config.constant.RoleType;
import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.AdminAccountDto;
import com.petkpetk.admin.dto.UserAccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminAccountResponse {

	private Long id;
	private String email;
	private String password;
	private String name;
	private Set<RoleType> roles = new LinkedHashSet<>();
	private boolean approved;
	private LocalDateTime createdAt;

	public static AdminAccountResponse from(AdminAccountDto dto) {
		return EntityAndDtoConverter.convertToDto(dto, AdminAccountResponse.class);
	}

}
