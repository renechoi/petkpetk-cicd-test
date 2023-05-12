package com.petkpetk.service.domain.user.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.petkpetk.service.common.AuditingFields;
import com.petkpetk.service.config.converter.EntityAndDtoConverter;
import com.petkpetk.service.config.converter.RoleTypeConverter;
import com.petkpetk.service.domain.user.constant.RoleType;
import com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo;
import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.dto.request.UserUpdateRequest;
import com.petkpetk.service.domain.user.entity.embedded.Address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Table(indexes = {@Index(columnList = "email"), @Index(columnList = "createdAt"), @Index(columnList = "createdBy")})
@Entity
@Where(clause = "deleted_yn='N'")
public class UserAccount extends AuditingFields implements Serializable {

	//Todo: 칼럼 length 제한 설정

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_account_id")
	private Long id;

	@Column(unique = true)
	private String email;

	private String password;

	private String name;

	private String nickname;

	@OneToOne(mappedBy = "userAccount", cascade = CascadeType.ALL)
	@ToString.Exclude
	private ProfileImage profileImage;
	@Embedded
	private Address address;

	@Column(name = "oauth2_provider_info")
	@Enumerated(EnumType.STRING)
	private OAuth2ProviderInfo OAuth2ProviderInfo;

	@Column(nullable = false)
	@Convert(converter = RoleTypeConverter.class)
	private Set<RoleType> roles = new LinkedHashSet<>();

	private String phoneNumber;

	private String businessName;

	private String businessNumber;

	public UserAccount(String email, String password, String name, String nickname, ProfileImage profileImage, Address address,
		 OAuth2ProviderInfo OAuth2ProviderInfo, Set<RoleType> roles) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.address = address;
		this.OAuth2ProviderInfo = OAuth2ProviderInfo;
		this.roles = roles;
	}

	public UserAccount(String email, String password, String name, String nickname, ProfileImage profileImage, Address address,
		OAuth2ProviderInfo OAuth2ProviderInfo, Set<RoleType> roles, String phoneNumber, String businessName, String businessNumber) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.profileImage = mapImage(profileImage);
		this.address = address;
		this.OAuth2ProviderInfo = OAuth2ProviderInfo;
		this.roles = roles;
		this.phoneNumber = phoneNumber;
		this.businessName = businessName;
		this.businessNumber = businessNumber;
	}

	public static UserAccount of(String email, String password, String name, String nickname, Address address,
		ProfileImage profileImage, OAuth2ProviderInfo OAuth2ProviderInfo, Set<RoleType> roles) {
		return new UserAccount(email, password, name, nickname,profileImage, address,  OAuth2ProviderInfo, roles);
	}

	public static UserAccount of(String email, String password, String name, String nickname,ProfileImage profileImage, Address address,
		 OAuth2ProviderInfo OAuth2ProviderInfo, Set<RoleType> roles,  String phoneNumber, String businessName, String businessNumber) {
		return new UserAccount(email, password, name, nickname, profileImage, address, OAuth2ProviderInfo, roles, phoneNumber, businessName, businessNumber);
	}

	public static UserAccount from(UserAccountDto userAccountDto) {
		return EntityAndDtoConverter.convertToEntity(userAccountDto, UserAccount.class);
	}

	private ProfileImage mapImage(ProfileImage profile) {
		profile.mapWith(this);
		return profile;
	}

	public void addImage(ProfileImage profileImage){
		profileImage.mapWith(this);
		this.profileImage = profileImage;
	}

	public UserAccount encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
		return this;
	}

	public boolean checkPassword(String thatPassword, PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(thatPassword, this.password);
	}


	public void update(UserUpdateRequest userUpdateRequest, ProfileImage profileImage) {
		this.email = userUpdateRequest.getEmail();
		this.name = userUpdateRequest.getName();
		this.nickname = userUpdateRequest.getNickname();
		this.profileImage = mapImage(profileImage);
		this.address = userUpdateRequest.getAddress();
		this.roles = userUpdateRequest.getRoles();
	}


	public void update(UserUpdateRequest userUpdateRequest) {
		this.email = userUpdateRequest.getEmail();
		this.name = userUpdateRequest.getName();
		this.nickname = userUpdateRequest.getNickname();
		this.address = userUpdateRequest.getAddress();
		this.roles = userUpdateRequest.getRoles();
	}

	@PrePersist
	public void anonymousSetup() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || "anonymousUser".equals(authentication.getName())
		) {
			this.createdBy = this.getName();
			this.modifiedBy = this.getName();
		}
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (!(that instanceof UserAccount)) {
			return false;
		}
		return this.getId() != null && this.getId().equals(((UserAccount)that).getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId());
	}

}
