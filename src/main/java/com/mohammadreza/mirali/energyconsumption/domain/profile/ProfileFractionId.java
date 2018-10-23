package com.mohammadreza.mirali.energyconsumption.domain.profile;

import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;

import java.io.Serializable;


/**
 * This is a composit primary key for MeterReadingEntity
 */
public class ProfileFractionId implements Serializable {


    private ProfileEntity profileEntity;
    private MonthEnum month;

    public ProfileFractionId(ProfileEntity profileEntity, MonthEnum month) {
        this.profileEntity = profileEntity;
        this.month = month;
    }

    public ProfileFractionId() {

    }
}
