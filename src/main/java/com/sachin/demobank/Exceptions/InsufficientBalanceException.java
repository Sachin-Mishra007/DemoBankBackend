package com.sachin.demobank.Exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message)
    {
        super(message);
    }
}
