package com.petkpetk.service.domain.user.dto.request;

import java.util.Set;

import javax.validation.Valid;
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
public class UserUpdateRequest {

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

	public UserUpdateRequest(Long id, String email, String password, String name, String nickname,
		Address address,
		com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo OAuth2ProviderInfo,
		Set<RoleType> roles, String phoneNumber, String businessName, String businessNumber) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.address = address;
		this.OAuth2ProviderInfo = OAuth2ProviderInfo;
		this.roles = roles;
		this.phoneNumber = phoneNumber;
		this.businessName = businessName;
		this.businessNumber = businessNumber;
	}

	public static UserUpdateRequest from(UserAccount userAccount, MultipartFile profileImage) {
		return new UserUpdateRequest(userAccount.getId(), userAccount.getEmail(), userAccount.getPassword(),
			userAccount.getName(), userAccount.getNickname(), profileImage, userAccount.getAddress(),userAccount.getOAuth2ProviderInfo(),userAccount.getRoles(),
			userAccount.getPhoneNumber(), userAccount.getBusinessName(), userAccount.getBusinessNumber());
	}

	public static UserUpdateRequest from(UserAccount userAccount) {
		return new UserUpdateRequest(
			userAccount.getId(), userAccount.getEmail(), userAccount.getPassword(),
			userAccount.getName(), userAccount.getNickname(), userAccount.getAddress(),userAccount.getOAuth2ProviderInfo(),userAccount.getRoles(),
			userAccount.getPhoneNumber(), userAccount.getBusinessName(), userAccount.getBusinessNumber()
		);
	}

	public UserAccount toEntity() {
		return EntityAndDtoConverter.convertToEntity(this, UserAccount.class);
	}

	public UserAccount toEntity(ProfileImage profileImage) {
		UserAccount userAccount = EntityAndDtoConverter.convertToEntity(this, UserAccount.class);
		userAccount.setProfileImage(profileImage);
		return userAccount;
	}
}
