package com.mohammadreza.mirali.energyconsumption.domain.meter.validation;

import com.mohammadreza.mirali.energyconsumption.domain.TestCaseData;
import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterEntity;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterReadingEntity;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterReadingServiceTest;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionServiceTest;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by mmirali on 21/10/2018.
 */
public class ConsumptionToleranceValidationTest {
    @Test
    public void validate() throws Exception {

        FractionShoudBeFound mockFractionShoudBeFound = new FractionShoudBeFound();
        MeterEntity meterEntity = TestCaseData.getPreperedMeterSorted();
        mockFractionShoudBeFound.validate(meterEntity);
        ConsumptionToleranceValidation consumptionToleranceValidation = new ConsumptionToleranceValidation();
        consumptionToleranceValidation.validate(meterEntity);

    }

    @Test(expected = ValidationException.class)
    public void validateExpectedException() throws Exception {

        FractionShoudBeFound mockFractionShoudBeFound = new FractionShoudBeFound();
        MeterEntity meterEntity = TestCaseData.getPreperedMeterSorted();
        meterEntity.getMeterReadingEntityList().get(0).setReadedMeter(meterEntity.getMeterReadingEntityList().get(0).getReadedMeter()*2);
        mockFractionShoudBeFound.validate(meterEntity);
        ConsumptionToleranceValidation consumptionToleranceValidation = new ConsumptionToleranceValidation();
        consumptionToleranceValidation.validate(meterEntity);

    }



}