package com.petkpetk.service.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServerProperty {
	IMAGE_SERVER_LOCATION("/images/item/");

	private final String serverLocation;
}
