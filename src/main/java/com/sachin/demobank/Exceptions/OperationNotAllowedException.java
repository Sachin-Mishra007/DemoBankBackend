package com.sachin.demobank.Exceptions;

public class OperationNotAllowedException extends RuntimeException{
    public OperationNotAllowedException(String message)
    {
        super(message);
    }

}
