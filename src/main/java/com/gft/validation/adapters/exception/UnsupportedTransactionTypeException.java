package com.gft.validation.adapters.exception;

public class UnsupportedTransactionTypeException extends RuntimeException {

    private static final String errorMessage = "Unsupported transaction type exception!";

    public UnsupportedTransactionTypeException() {
        super(errorMessage);
    }
}
