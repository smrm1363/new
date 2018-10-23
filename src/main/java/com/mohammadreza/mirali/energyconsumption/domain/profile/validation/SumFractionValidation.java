package com.mohammadreza.mirali.energyconsumption.domain.profile.validation;


import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationRule;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;


/**
 * This is the validation For a given Profile the sum of all fractions should be 1.
 * This is a strategy
 */
public class SumFractionValidation implements ValidationRule {

    @Override
    public void validate(Object o) throws ValidationException {
        ProfileEntity profileEntity = (ProfileEntity) o;
        Double total = profileEntity.getProfileFractionEntityList()
                .stream().mapToDouble(value -> value.getFraction()).sum();
        if(total != 1)
            throw new ValidationException("Sum of fractions in profile : "+profileEntity.getId()+" should be 1");

    }


}
