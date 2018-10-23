package com.mohammadreza.mirali.energyconsumption.domain.common;


/**
 * This is an interface for our validation Strategy. Each validation logic should implement is
 * I used Strategy design pattern to have different validations.
 * @param <T> is teh type Of our entity to be validated
 */
@FunctionalInterface
public interface ValidationRule<T> {
    void validate(T t) throws ValidationException;

}
