package com.petkpetk.admin.config.constant;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ImgurImageError {
	@JsonProperty("code")
	private int code;

	@JsonProperty("message")
	private String message;

	@JsonProperty("type")
	private String type;

	@JsonProperty("exception")
	private List<String> exception;


}