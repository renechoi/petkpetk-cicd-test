package com.petkpetk.service.domain.user.dto.request;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.domain.user.constant.RoleType;
import com.petkpetk.service.config.converter.EntityAndDtoConverter;
import com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo;
import com.petkpetk.service.domain.user.entity.ProfileImage;
import com.petkpetk.service.domain.user.entity.UserAccount;
import com.petkpetk.service.domain.user.entity.embedded.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupRequest {

	private Long id;

	private String email;

	private String password;
	private String name;
	private String nickname;

	private MultipartFile profileImage;
	private Address address;
	private OAuth2ProviderInfo OAuth2ProviderInfo;
	private Set<RoleType> roles;
	private String phoneNumber;
	private String businessName;
	private String businessNumber;

	public UserAccount toEntity() {
		return EntityAndDtoConverter.convertToEntity(this, UserAccount.class);
	}

	public UserAccount toEntity(ProfileImage profileImage) {
		UserAccount userAccount = EntityAndDtoConverter.convertToEntity(this, UserAccount.class);
		userAccount.addImage(profileImage);
		return userAccount;
	}
}
