package com.banking.accountledger.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message)  {
        super(message);
    }
}
