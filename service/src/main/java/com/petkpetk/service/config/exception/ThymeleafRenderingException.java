package com.petkpetk.service.config.exception;

import com.petkpetk.service.common.StatusCode;

public class ThymeleafRenderingException extends PetkpetkServerException {

	public ThymeleafRenderingException(StatusCode statusCode, String detailMessage) {
		super(statusCode, detailMessage);
	}
}
