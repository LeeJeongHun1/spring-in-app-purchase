package com.springinapppurchase.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends CustomException {
    public ForbiddenException(String errorCode) {
        super(HttpStatus.FORBIDDEN, errorCode);
    }
}
