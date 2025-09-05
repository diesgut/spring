package com.diesgut.saga.commons.exceptions;

public class CreditCardProcessorUnavailableException extends RuntimeException {

    public CreditCardProcessorUnavailableException(Throwable cause) {
        super(cause);
    }
}
