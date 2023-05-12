package com.petkpetk.service.config.exception;

import com.petkpetk.service.common.StatusCode;

import lombok.Getter;

@Getter
public class PetkpetkServerException extends RuntimeException{

	private StatusCode statusCode;
	private String detailMessage;

	public PetkpetkServerException() {
		super();
		this.statusCode = StatusCode.INTERNAL_SERVER_ERROR;
		this.detailMessage = "";
	}

	public PetkpetkServerException(StatusCode statusCode) {
		super(statusCode.getMessage());
		
		this.statusCode = statusCode;
		this.detailMessage = statusCode.getMessage();
	}

	public PetkpetkServerException(StatusCode statusCode, String detailMessage) {
		super(detailMessage);
		this.statusCode = statusCode;
		this.detailMessage = detailMessage;
	}
}
