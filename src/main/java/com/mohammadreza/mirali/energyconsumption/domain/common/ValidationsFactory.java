package com.mohammadreza.mirali.energyconsumption.domain.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is the Factory for creating a list of ValidationRule.
 * I used a combination of Strategy and Factory design patterns.
 * It create the ValidationRules dynamically with reading the application.properties file.
 * If in the future we wand to add another validation, we just need to add the class name of the validation in to the application.properties file.
 */
@Component("ValidationsFactory")
public class ValidationsFactory {

    /**
     * I injected it to read the application.properties file.
     */
    private final Environment env;

    @Autowired
    public ValidationsFactory(Environment env) {
        this.env = env;
    }

    /**
     * It returns a list of the ValidationRule dynamically with reading the application.properties file.
     * If in the future we wand to add another validation, we just need to add the class name of the validation in to the application.properties file.
     * It return a validation chain
     * @param propertyKey is the KEY of the property file.
     * @return  a list of the ValidationRule
     */
    public List<ValidationRule> getValidationRulesByPropertyName(String propertyKey)
    {
        /**
         * The reference of different ValidationRule Classes should be seperated by comma.
         */
        String[] validationArray = env.getProperty(propertyKey).split(",");
        List<ValidationRule> validationRuleList = new ArrayList<>();
        Arrays.stream(validationArray).forEach(s -> {


            try {
                validationRuleList.add((ValidationRule) Class.forName(s).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        });
        return validationRuleList;
    }
}
