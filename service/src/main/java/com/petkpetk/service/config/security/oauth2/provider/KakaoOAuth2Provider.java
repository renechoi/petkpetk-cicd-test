package com.petkpetk.service.config.security.oauth2.provider;

import static com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

@Component
public class KakaoOAuth2Provider extends OAuth2Provider {
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String kakaoClientId;

	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String kakaoClientSecret;

	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String kakaoRedirectUri;

	@Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
	private String kakaoTokenUri;

	@Value("${spring.security.oauth2.client.provider.kakao.authorization-uri}")
	private String kakaoAuthorizationUri;

	@Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
	private String kakaoUserInfoUri;

	@Value("${spring.security.oauth2.client.provider.kakao.user-name-attribute}")
	private String kakaoUserNameAttribute;

	@Override
	public ClientRegistration getClientRegistration() {
		return ClientRegistration.withRegistrationId(KAKAO.getProviderId())
			.clientId(kakaoClientId)
			.clientSecret(kakaoClientSecret)
			.clientAuthenticationMethod(ClientAuthenticationMethod.POST)
			.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
			.redirectUri(kakaoRedirectUri)
			.authorizationUri(kakaoAuthorizationUri)
			.tokenUri(kakaoTokenUri)
			.userInfoUri(kakaoUserInfoUri)
			.userNameAttributeName(kakaoUserNameAttribute)
			.build();
	}
}
