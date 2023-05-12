package com.petkpetk.admin.dto.response;

import java.util.List;

import com.petkpetk.admin.dto.AdminAccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminAccountWaitingsResponse {
	private List<AdminAccountDto> adminAccounts;

	public static AdminAccountWaitingsResponse of(List<AdminAccountDto> adminAccountDtos) {
		return new AdminAccountWaitingsResponse(adminAccountDtos);
	}
}
