package com.petkpetk.service.domain.user.dto.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.petkpetk.service.domain.user.constant.RoleType;
import com.petkpetk.service.config.converter.EntityAndDtoConverter;
import com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo;
import com.petkpetk.service.domain.user.entity.ProfileImage;
import com.petkpetk.service.domain.user.entity.UserAccount;
import com.petkpetk.service.domain.user.entity.embedded.AddressClaim;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OAuth2UserAccountPrincipal extends UserAccountPrincipal implements UserDetails, Serializable, OAuth2User,
	OidcUser {

	private Long id;

	private String email;
	private String password;

	private String name;

	private String nickname;

	private ProfileImage profileImage;

	private AddressClaim address;

	private OAuth2ProviderInfo OAuth2ProviderInfo;

	private Collection<? extends GrantedAuthority> roles;

	private String nameAttributeKey;
	private Map<String, Object> attributes;

	private OidcIdToken idToken;

	private OidcUserInfo userInfo;

	public OAuth2UserAccountPrincipal(String email, String name, String nickname, ProfileImage profileImage,
		com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo OAuth2ProviderInfo,
		Collection<? extends GrantedAuthority> roles, String nameAttributeKey, Map<String, Object> attributes) {
		this.email = email;
		this.name = name;
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.OAuth2ProviderInfo = OAuth2ProviderInfo;
		this.roles = roles;
		this.nameAttributeKey = nameAttributeKey;
		this.attributes = attributes;
	}

	public OAuth2UserAccountPrincipal(String email, String name, String nickname, ProfileImage profileImage,
		com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo OAuth2ProviderInfo,
		Collection<? extends GrantedAuthority> roles, String nameAttributeKey, Map<String, Object> attributes,
		OidcIdToken idToken, OidcUserInfo userInfo) {
		this.email = email;
		this.name = name;
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.OAuth2ProviderInfo = OAuth2ProviderInfo;
		this.roles = roles;
		this.nameAttributeKey = nameAttributeKey;
		this.attributes = attributes;
		this.idToken = idToken;
		this.userInfo = userInfo;
	}

	public static OAuth2UserAccountPrincipal fromOAuth2(Map<String, Object> attributes) {
		return new OAuth2UserAccountPrincipal(
			fetchEmail(attributes),
			fetchName(attributes),
			fetchNickname(attributes),
			fetchProfileImage(attributes),
			fetchProviderInfo(attributes),
			fetchRoles(),
			fetchNameAttributeKey(attributes),
			attributes
		);
	}

	public static OAuth2UserAccountPrincipal fromOidc(Map<String, Object> attributes) {
		return new OAuth2UserAccountPrincipal(
			fetchEmail(attributes),
			fetchName(attributes),
			fetchNickname(attributes),
			fetchProfileImage(attributes),
			fetchProviderInfo(attributes),
			fetchRoles(),
			fetchNameAttributeKey(attributes),
			attributes,
			fetchIdToken(attributes),
			fetchUserInfo(attributes)
		);
	}

	public UserAccount toEntity() {
		UserAccount userAccount = EntityAndDtoConverter.convertToEntity(this, UserAccount.class);
		userAccount.setRoles(Set.of(RoleType.USER));
		userAccount.addImage(profileImage);
		return userAccount;
	}

	private static String fetchName(Map<String, Object> attributes) {
		return attributes.get("name") == null ? attributes.get("nickname").toString() :
			attributes.get("name").toString();
	}

	private static String fetchEmail(Map<String, Object> attributes) {
		return attributes.get("email").toString();
	}

	private static String fetchNickname(Map<String, Object> attributes) {
		return attributes.get("nickname") == null ? attributes.get("name").toString() :
			attributes.get("nickname").toString();
	}

	private static ProfileImage fetchProfileImage(Map<String, Object> attributes) {
		try {
			return attributes.get("profile_image") == null ? ProfileImage.of(attributes.get("picture").toString()) :
				ProfileImage.of(attributes.get("profile_image").toString());
		} catch (Exception ignored) {
			return new ProfileImage();
		}

	}

	private static OAuth2ProviderInfo fetchProviderInfo(
		Map<String, Object> attributes) {
		return com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo.valueOf(
			attributes.get("providerInfo").toString().toUpperCase());
	}

	private static Set<SimpleGrantedAuthority> fetchRoles() {
		return Set.of(RoleType.USER).stream()
			.map(RoleType::getRoleName)
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toUnmodifiableSet());
	}

	private static String fetchNameAttributeKey(Map<String, Object> attributes) {
		return attributes.get("nameAttributeKey").toString();
	}

	private static OidcIdToken fetchIdToken(Map<String, Object> attributes) {
		return (OidcIdToken)attributes.get("idToken");
	}

	private static OidcUserInfo fetchUserInfo(Map<String, Object> attributes) {
		return (OidcUserInfo)attributes.get("userInfo");
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public Map<String, Object> getClaims() {
		return this.attributes;
	}

	@Override
	public OidcUserInfo getUserInfo() {
		return this.userInfo;
	}

	@Override
	public OidcIdToken getIdToken() {
		return this.idToken;
	}
}
