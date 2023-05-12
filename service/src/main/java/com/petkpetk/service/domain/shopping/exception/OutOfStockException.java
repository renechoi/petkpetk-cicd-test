package com.petkpetk.service.domain.shopping.exception;

import com.petkpetk.service.common.StatusCode;
import com.petkpetk.service.config.exception.PetkpetkServerException;

public class OutOfStockException extends  PetkpetkServerException{

	private static final StatusCode statusCode = StatusCode.OUT_OF_STOCK;

	public OutOfStockException(String message) {
		super(statusCode);
	}

	public OutOfStockException() {
		super();
	}
}
