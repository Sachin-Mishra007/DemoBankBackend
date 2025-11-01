package com.sachin.demobank.Exceptions;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String message)
    {
        super(message);
    }

}
