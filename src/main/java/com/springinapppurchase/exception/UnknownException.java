package com.springinapppurchase.exception;

public class UnknownException extends RuntimeException {
    public UnknownException() {
        super("unknown exception occurred.");
    }
}
