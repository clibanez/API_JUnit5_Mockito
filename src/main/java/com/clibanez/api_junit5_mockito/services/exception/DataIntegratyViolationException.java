package com.clibanez.api_junit5_mockito.services.exception;

public class DataIntegratyViolationException extends RuntimeException {


    public DataIntegratyViolationException(String message) {
        super(message);
    }

    public DataIntegratyViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}