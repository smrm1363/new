package com.mohammadreza.mirali.energyconsumption.domain.common;

/**
 * This is a specific Exception type for our validation
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }

}
