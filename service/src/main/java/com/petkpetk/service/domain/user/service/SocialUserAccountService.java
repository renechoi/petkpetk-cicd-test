package com.petkpetk.service.domain.user.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo;
import com.petkpetk.service.config.security.oauth2.SocialAccount;
import com.petkpetk.service.domain.user.dto.security.OAuth2UserAccountPrincipal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@SuppressWarnings("unchecked")
public class SocialUserAccountService<T extends OAuth2UserRequest, U extends OAuth2User> implements
	OAuth2UserService<T, OAuth2UserAccountPrincipal> {
	private UserAccountService userAccountService;

	private final Map<String, SocialAccount> socialUsers;

	@Autowired
	public SocialUserAccountService(UserAccountService userAccountService) {
		this.userAccountService = userAccountService;
		socialUsers = Map.of(OAuth2ProviderInfo.KAKAO.getProviderId(), KAKAO, OAuth2ProviderInfo.NAVER.getProviderId(),
			NAVER, OAuth2ProviderInfo.GOOGLE.getProviderId(), GOOGLE);
	}


	@Override
	public OAuth2UserAccountPrincipal loadUser(T userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<T, U> oAuth2UserService = (OAuth2UserService<T, U>)new DefaultOAuth2UserService();

		SocialAccount socialAccount = socialUsers.get(
			userRequest.getClientRegistration().getRegistrationId().toLowerCase());

		if (userRequest instanceof OidcUserRequest) {
			oAuth2UserService = (OAuth2UserService<T, U>)new OidcUserService();
		}

		return socialAccount.signup(oAuth2UserService.loadUser(userRequest));
	}

	public SocialAccount KAKAO = (oAuth2User) -> {
		OAuth2UserAccountPrincipal oAuth2UserAccountPrincipal = OAuth2UserAccountPrincipal.fromOAuth2(
			getKakaoAttributes(oAuth2User));
		userAccountService.saveSocialUser(oAuth2UserAccountPrincipal);
		return oAuth2UserAccountPrincipal;
	};

	public SocialAccount NAVER = (oAuth2User) -> {
		OAuth2UserAccountPrincipal oAuth2UserAccountPrincipal = OAuth2UserAccountPrincipal.fromOAuth2(
			getNaverAttributes(oAuth2User));
		userAccountService.saveSocialUser(oAuth2UserAccountPrincipal);
		return oAuth2UserAccountPrincipal;
	};

	public SocialAccount GOOGLE = (oidcUser) -> {
		OAuth2UserAccountPrincipal oAuth2UserAccountPrincipal = OAuth2UserAccountPrincipal.fromOidc(
			getGoogleAttributes(oidcUser));
		userAccountService.saveSocialUser(oAuth2UserAccountPrincipal);
		return oAuth2UserAccountPrincipal;
	};

	private Map<String, Object> getKakaoAttributes(OAuth2User oAuth2User) {
		Map<String, Object> kakaoAttributes = Stream.of("properties", "kakao_account")
			.flatMap(key -> ((Map<String, Object>)oAuth2User.getAttribute(key)).entrySet().stream())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		kakaoAttributes.put("providerInfo", "kakao");
		kakaoAttributes.put("nameAttributeKey", "id");
		return kakaoAttributes;
	}

	private Map<String, Object> getNaverAttributes(OAuth2User oAuth2User) {
		Map<String, Object> naverAttributes = oAuth2User.getAttribute("response");
		naverAttributes.put("providerInfo", "naver");
		naverAttributes.put("nameAttributeKey", "response");
		return naverAttributes;
	}

	private Map<String, Object> getGoogleAttributes(OAuth2User oAuth2User) {
		OidcUser oidcUser = (OidcUser)oAuth2User;
		Map<String, Object> googleAttributes = new HashMap<>(oidcUser.getAttributes());
		googleAttributes.put("providerInfo", "google");
		googleAttributes.put("nameAttributeKey", "sub");
		googleAttributes.put("idToken", oidcUser.getIdToken());
		googleAttributes.put("userInfo", oidcUser.getUserInfo());
		return googleAttributes;
	}

}
