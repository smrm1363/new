package com.mohammadreza.mirali.energyconsumption.domain.profile.validation;

import com.mohammadreza.mirali.energyconsumption.domain.TestCaseData;
import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionServiceTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mmirali on 21/10/2018.
 */
public class SumFractionValidationTest {
    @Test
    public void validate() throws Exception {
        ProfileEntity profileEntity = TestCaseData.getPreperedProfile();
        SumFractionValidation sumFractionValidation = new SumFractionValidation();
        sumFractionValidation.validate(profileEntity);


    }

    @Test(expected = ValidationException.class)
    public void validateExpectedException() throws Exception {
        ProfileEntity profileEntity = TestCaseData.getPreperedProfile();
        profileEntity.getProfileFractionEntityList().get(0).setFraction(1.0);
        SumFractionValidation sumFractionValidation = new SumFractionValidation();
        sumFractionValidation.validate(profileEntity);


    }
}