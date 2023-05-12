package com.petkpetk.service.domain.user.exception;

import com.petkpetk.service.common.StatusCode;
import com.petkpetk.service.config.exception.PetkpetkServerException;

public class UserNotFoundException extends PetkpetkServerException {

    private static final StatusCode statusCode = StatusCode.USER_NOT_FOUND;

    public UserNotFoundException(){
        super(statusCode);
    }
}
