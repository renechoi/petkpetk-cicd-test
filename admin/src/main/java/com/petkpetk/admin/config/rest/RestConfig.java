package com.petkpetk.admin.config.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.client.RestTemplate;

import com.petkpetk.admin.entity.AdminAccount;
import com.petkpetk.admin.entity.QnaAnswer;
import com.petkpetk.admin.entity.ShoppingNotice;

@Configuration
public class RestConfig {

	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {
		return RepositoryRestConfigurer.withConfig((config, cors) ->
			config
				.exposeIdsFor(AdminAccount.class)
				.exposeIdsFor(ShoppingNotice.class)
				.exposeIdsFor(QnaAnswer.class)

		);
	}


	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}
}


