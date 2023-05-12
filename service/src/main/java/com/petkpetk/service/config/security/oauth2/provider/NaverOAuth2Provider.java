package com.petkpetk.service.config.security.oauth2.provider;

import static com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

@Component
public class NaverOAuth2Provider extends OAuth2Provider {
	@Value("${spring.security.oauth2.client.registration.naver.client-id}")
	private String naverClientId;

	@Value("${spring.security.oauth2.client.registration.naver.client-secret}")
	private String naverClientSecret;

	@Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
	private String naverRedirectUri;

	@Value("${spring.security.oauth2.client.provider.naver.token-uri}")
	private String naverTokenUri;

	@Value("${spring.security.oauth2.client.provider.naver.authorization-uri}")
	private String naverAuthorizationUri;

	@Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
	private String naverUserInfoUri;

	@Value("${spring.security.oauth2.client.provider.naver.user-name-attribute}")
	private String naverUserNameAttribute;

	@Override
	public ClientRegistration getClientRegistration() {
		return ClientRegistration.withRegistrationId(NAVER.getProviderId())
			.clientId(naverClientId)
			.clientSecret(naverClientSecret)
			.clientAuthenticationMethod(ClientAuthenticationMethod.POST)
			.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
			.redirectUri(naverRedirectUri)
			.scope("name", "email", "profile_image")
			.authorizationUri(naverAuthorizationUri)
			.tokenUri(naverTokenUri)
			.userInfoUri(naverUserInfoUri)
			.userNameAttributeName(naverUserNameAttribute)
			.build();
	}
}