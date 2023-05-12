package com.petkpetk.admin.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAccountDto {
	private String deletedYn;
	private String createdAt;
	private String createdBy;
	private String modifiedAt;
	private String modifiedBy;
	private Long id;
	private String email;
	private String password;
	private String name;
	private String nickname;
	private AddressDto address;
	private List<String> roles;
	private String phoneNumber;
	private String businessName;
	private String businessNumber;
	private String oauth2ProviderInfo;

}