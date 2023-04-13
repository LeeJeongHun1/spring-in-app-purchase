package com.springinapppurchase.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends CustomException {
    public InternalServerErrorException(String errorCode) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, errorCode);
    }
}
