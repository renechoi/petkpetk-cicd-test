package com.petkpetk.service.domain.shopping.exception;

import com.petkpetk.service.common.StatusCode;
import com.petkpetk.service.config.exception.PetkpetkServerException;

public class CancellationInProgressException extends PetkpetkServerException {

	public CancellationInProgressException(StatusCode statusCode) {
		super(statusCode);
	}
}
