package com.petkpetk.admin.dto.response;

import java.util.List;

import com.petkpetk.admin.dto.UserAccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountApiResponse {

	private Embedded _embedded;
	private UserAccountDto userAccount;

	public static UserAccountApiResponse empty() {
		return new UserAccountApiResponse(new Embedded(List.of()), new UserAccountDto());
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Embedded {
		private List<UserAccountDto> UserAccounts;
	}

}




