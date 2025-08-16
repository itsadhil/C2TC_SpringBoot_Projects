package com.tns.one_to_many_mapping.exception;

public class CustomerNotFoundException extends RuntimeException {
    // Constructor accepting a custom message
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
