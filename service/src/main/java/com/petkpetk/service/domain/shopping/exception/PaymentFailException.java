package com.petkpetk.service.domain.shopping.exception;

import com.petkpetk.service.common.StatusCode;
import com.petkpetk.service.config.exception.PetkpetkServerException;

public class PaymentFailException extends PetkpetkServerException {

	private static final StatusCode statusCode = StatusCode.PAY_FAILED;

	public PaymentFailException(StatusCode statusCode) {
		super(statusCode);
	}
}
