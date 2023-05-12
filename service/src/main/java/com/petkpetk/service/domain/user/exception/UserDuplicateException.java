package com.petkpetk.service.domain.user.exception;

import com.petkpetk.service.common.StatusCode;
import com.petkpetk.service.config.exception.PetkpetkServerException;

public class UserDuplicateException extends PetkpetkServerException {

    private static final StatusCode statusCode = StatusCode.USER_DUPLICATE;

    public UserDuplicateException() {
        super(statusCode);
    }
}
