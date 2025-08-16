package com.tns.one_to_many_mapping.exception;

public class OrderNotFoundException extends RuntimeException {
    // Constructor accepting a custom message
    public OrderNotFoundException(String message) {
        super(message);
    }
}
