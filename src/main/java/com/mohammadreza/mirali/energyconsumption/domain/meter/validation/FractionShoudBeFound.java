package com.mohammadreza.mirali.energyconsumption.domain.meter.validation;


import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationRule;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterEntity;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterReadingEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionEntity;


/**
 * This is the validation for Fractions for the profiles contained in the data should exist.
 * This is a strategy
 */
public class FractionShoudBeFound implements ValidationRule {

    @Override
    public void validate(Object o) throws ValidationException {
        MeterEntity meterEntity = (MeterEntity) o;

        if(meterEntity.getProfileEntity() != null)
        {
            if(meterEntity.getProfileEntity().getProfileFractionEntityList() != null)
            {
                Boolean found = false;
                for(MeterReadingEntity meterReadingEntity:meterEntity.getMeterReadingEntityList())
                {
                    found = false;
                    for(ProfileFractionEntity profileFractionEntity:meterEntity.getProfileEntity().getProfileFractionEntityList())
                    {
                        if(meterReadingEntity.getMonth().equals(profileFractionEntity.getMonth()))
                        {
                            found = true;
                            meterReadingEntity.setMatchedProfileFractionEntity(profileFractionEntity);
                            continue;
                        }
                    }
                    if(found == false)
                        break;

                }
                if(found == true)
                    return;

            }
        }

        throw new ValidationException("Fractions for the profiles contained in the data should exist, the Meter with ID number "+meterEntity.getId()+" has problem");

        


    }


}
