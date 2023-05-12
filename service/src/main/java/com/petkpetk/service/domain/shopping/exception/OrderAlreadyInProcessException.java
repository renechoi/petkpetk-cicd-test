package com.petkpetk.service.domain.shopping.exception;

import com.petkpetk.service.common.StatusCode;
import com.petkpetk.service.config.exception.PetkpetkServerException;

public class OrderAlreadyInProcessException extends PetkpetkServerException {

	public OrderAlreadyInProcessException() {
		super(StatusCode.ORDER_ALREADY_IN_PROCESS);
	}
}
