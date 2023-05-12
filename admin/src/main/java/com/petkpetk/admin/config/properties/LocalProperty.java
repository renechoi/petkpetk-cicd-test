package com.petkpetk.admin.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Component
@Getter
@RequiredArgsConstructor
@PropertySource(value = "classpath:application-dev.yml")
public class LocalProperty {

	@Value("${imgurClientId}")
	private String imgurClientId;

	@Value("${imgurClientSecret}")
	private String imgurClientSecret;

	public static LocalProperty getInstance() {
		return ApplicationContextProvider.getApplicationContext().getBean(LocalProperty.class);
	}
}




