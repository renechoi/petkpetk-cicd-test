package com.petkpetk.service.config.security.oauth2;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.petkpetk.service.domain.user.dto.security.OAuth2UserAccountPrincipal;

@FunctionalInterface
public interface SocialAccount {
	OAuth2UserAccountPrincipal signup(OAuth2User oAuth2User) throws OAuth2AuthenticationException;
}



