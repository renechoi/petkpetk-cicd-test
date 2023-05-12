package com.petkpetk.service.config.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import com.petkpetk.service.config.security.oauth2.provider.GoogleOAuth2Provider;
import com.petkpetk.service.config.security.oauth2.provider.KakaoOAuth2Provider;
import com.petkpetk.service.config.security.oauth2.provider.NaverOAuth2Provider;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OAuth2Config {

	private final GoogleOAuth2Provider googleOAuth2Provider;
	private final KakaoOAuth2Provider kakaoOAuth2Provider;
	private final NaverOAuth2Provider naverOAuth2Provider;

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		List<ClientRegistration> clientRegistrations = Arrays.asList(
			googleOAuth2Provider.getClientRegistration(),
			kakaoOAuth2Provider.getClientRegistration(),
			naverOAuth2Provider.getClientRegistration()
		);
		return new InMemoryClientRegistrationRepository(clientRegistrations);
	}

	@Bean
	public OAuth2AuthorizedClientService oAuth2AuthorizedClientService() {
		return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
	}
}






