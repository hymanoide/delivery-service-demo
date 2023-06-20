package com.openbravo.delivery.service.exceptions;


public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super(message);
        // TODO: Implement custom handling when order number is not found.
    }
}
