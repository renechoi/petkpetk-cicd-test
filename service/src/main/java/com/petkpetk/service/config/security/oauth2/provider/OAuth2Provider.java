package com.petkpetk.service.config.security.oauth2.provider;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Component;

@Component
public abstract class OAuth2Provider {
	protected String clientId;
	protected String clientSecret;
	protected String redirectUri;
	protected String tokenUri;
	protected String authorizationUri;
	protected String userInfoUri;
	protected String userNameAttribute;

	public abstract ClientRegistration getClientRegistration();

}