package com.petkpetk.service.domain.shopping.exception;

import com.petkpetk.service.common.StatusCode;
import com.petkpetk.service.config.exception.PetkpetkServerException;

public class UserAskNotFountException extends PetkpetkServerException {
	private static final StatusCode statusCode = StatusCode.NOT_FOUND_ASK;

	public UserAskNotFountException(){
		super(statusCode);
	}
}
