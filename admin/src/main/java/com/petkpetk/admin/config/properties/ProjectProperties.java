package com.petkpetk.admin.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties("project")
public class ProjectProperties {

	private PetkPetk petkPetk;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PetkPetk {
		private String url;
	}
}
