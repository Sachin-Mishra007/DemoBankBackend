package com.sachin.demobank.Exceptions;

public class AccountNotActiveException extends RuntimeException {
    public AccountNotActiveException(String msg)
    {
        super(msg);
    }

}
