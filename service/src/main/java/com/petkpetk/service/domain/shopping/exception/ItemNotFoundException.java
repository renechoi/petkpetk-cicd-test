package com.petkpetk.service.domain.shopping.exception;

import com.petkpetk.service.common.StatusCode;
import com.petkpetk.service.config.exception.PetkpetkServerException;

public class ItemNotFoundException extends  PetkpetkServerException{

	private static final StatusCode statusCode = StatusCode.ITEM_NOT_FOUND;

	public ItemNotFoundException(){
		super(statusCode);
	}
}
