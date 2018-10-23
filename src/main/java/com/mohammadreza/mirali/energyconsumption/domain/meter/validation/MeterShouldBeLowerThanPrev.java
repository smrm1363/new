package com.mohammadreza.mirali.energyconsumption.domain.meter.validation;


import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationRule;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterEntity;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterReadingEntity;

import java.util.List;
import java.util.stream.Collectors;


/**
 * This is the validation for a given meter, a reading for a month should not be lower than the previous one
 * This is a strategy
 */
public class MeterShouldBeLowerThanPrev implements ValidationRule {

    @Override
    public void validate(Object o) throws ValidationException {
        MeterEntity meterEntity = (MeterEntity) o;

        List<MeterReadingEntity> meterReadingEntityList = meterEntity.getMeterReadingEntityList().stream()
                .sorted((o1, o2) -> o1.getMonth().compareTo(o2.getMonth()) ).collect(Collectors.toList());
        for (int x=0;x<meterReadingEntityList.size();x++)
        {
            if(x>0)
            {
                if(meterReadingEntityList.get(x).getReadedMeter() < meterReadingEntityList.get(x-1).getReadedMeter())
                    throw new ValidationException("For a given meter ("+ meterEntity.getId() +") a reading for a month should not be lower than the previous one");

            }
        }

    }

}
