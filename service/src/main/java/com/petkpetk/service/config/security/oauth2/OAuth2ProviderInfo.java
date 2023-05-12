package com.petkpetk.service.config.security.oauth2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OAuth2ProviderInfo {

	GOOGLE("구글", "google"),
	NAVER("네이버", "naver"),
	KAKAO("카카오", "kakao");

	private final String providerName;
	private final String providerId;

}