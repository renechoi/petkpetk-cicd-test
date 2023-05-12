package com.petkpetk.service.config.exception;

import com.petkpetk.service.common.StatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse {

	private StatusCode statusCode;
	private String message;

	public static StatusResponse of (StatusCode statusCode, String message){
		return new StatusResponse(statusCode, message);
	}
}
