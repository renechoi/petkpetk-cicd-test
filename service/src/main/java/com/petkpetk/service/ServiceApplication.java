package com.petkpetk.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.petkpetk.service.common.StatusCode;
import com.petkpetk.service.config.exception.ThymeleafRenderingException;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		try{
			SpringApplication.run(ServiceApplication.class, args);
		} catch (Exception e){
			throw new ThymeleafRenderingException(StatusCode.THYMELEAF_RENDERING_FAILED, e.getMessage());
		}
	}
}
