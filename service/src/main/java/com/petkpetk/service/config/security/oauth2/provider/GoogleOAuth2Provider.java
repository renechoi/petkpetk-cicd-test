package com.petkpetk.service.config.security.oauth2.provider;

import static com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Component;

@Component
public class GoogleOAuth2Provider extends OAuth2Provider {
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Override
    public ClientRegistration getClientRegistration() {
        return CommonOAuth2Provider.GOOGLE.getBuilder(GOOGLE.getProviderId())
            .clientId(googleClientId)
            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
            .clientSecret(googleClientSecret)
            .build();
    }
}