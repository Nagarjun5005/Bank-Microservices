package com.nagarjun.Account.exception;

public class CustomerAlreadyExistsException extends RuntimeException {


    public CustomerAlreadyExistsException(String message) {
        super(message);
    }

}
