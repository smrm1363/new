package com.mohammadreza.mirali.energyconsumption.domain.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * RepositoryCompletion is our custom validation invoker, it is a Proxy for repository.saveAll.
 * I used it for save and update operation.
 */
@Component
public class RepositoryCompletion implements ValidatorInt{

    /**
     * This is an injected ValidationsFactory.
     */
    private final ValidationsFactory validationsFactory;
    @Autowired
    public RepositoryCompletion(ValidationsFactory validationsFactory) {
        this.validationsFactory = validationsFactory;
    }

    /**
     * This method is the responsible for check all validations, collect probable exceptions, and save correct entities.
     * @param entityList is the list of entities to be saved
     * @param repository is the responsible repository for save and update operation in Database
     * @param validationsProperyKey is a KEY in application.properties file, the value of this property is the name of our Validator classes
     * @param <T> is the type of our entity
     * @param <B> is the type of our repository
     * @throws ValidationException is the probable exception dou to our business logic
     */
    public <T,B extends JpaRepository> void saveEntityListWithValidation(List<T> entityList, B repository,String validationsProperyKey) throws ValidationException {
        /**
         *  The entities which does not have problem will be collected in this list
         */
        List<T> selectedEntityList = new ArrayList<>();
        /**
         * Dua to we want our validation logic in one entity do not block the others, I collected all Exception messages in a list
         */
        List<String> allExceptionMessages = new ArrayList<>();
        entityList.forEach(entity -> {
            String exeptionMessage = doValidations(entity,validationsFactory.getValidationRulesByPropertyName(validationsProperyKey));
            if(exeptionMessage != null)
            {
                allExceptionMessages.add(exeptionMessage);
                return;
            }
            else
            {
                selectedEntityList.add(entity);
            }

        });

        repository.saveAll(selectedEntityList);
        StringBuilder stringBuilder = new StringBuilder();
        allExceptionMessages.forEach(message -> {
            stringBuilder.append(message+" ; ");
        });
        if(allExceptionMessages.size()>0)
            throw new ValidationException(stringBuilder.toString());
    }
}
