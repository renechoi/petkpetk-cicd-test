package com.petkpetk.service.config.properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Component
@Getter
@RequiredArgsConstructor
@PropertySource(value = "classpath:application-dev.yml")
public class LocalProperty {

	@Value("${itemImageLocation}")
	private String itemLocalStorage;

	@Value("${kakaoPaymentTestCid}")
	private String kakaoPaymentCid;

	@Value("${kakaoPaymentAdminKey}")
	private String kakaoPaymentAdminKey;

	@Value("${SERVER_PORT}")
	private String serverPort;

	private final ServerPort serverPortProperties;

	public static LocalProperty getInstance() {
		return ApplicationContextProvider.getApplicationContext().getBean(LocalProperty.class);
	}

	@RequiredArgsConstructor
	@Getter
	@ConstructorBinding
	@ConfigurationProperties(prefix = "local.server")
	public static class ServerPort {
		private final String port;
	}

	public String getServerPort() {
		return serverPortProperties.getPort();
	}
}
