package com.mohammadreza.mirali.energyconsumption.domain.common;


import java.util.List;

/**
 * This is an interface which each validator should implement is.
 *
 */
public interface ValidatorInt {

    /**
     * This default method is for calling our validation logic
     * @param t
     * @param validationRuleList
     * @param <T>
     * @return
     */
    default <T> String doValidations(T t,List<ValidationRule> validationRuleList)  {
        String message = null;


        for(ValidationRule validationRule:validationRuleList)
        {
            try {
                validationRule.validate(t);
            } catch (ValidationException e) {

                e.printStackTrace();
                return e.getMessage();

            }
        }

        return null;

    }
}
