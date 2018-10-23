package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;

import java.io.Serializable;


/**
 * This is a composit primary key for MeterReadingEntity
 */
public class MeterReadingId implements Serializable {


    private MeterEntity meterEntity;
    private MonthEnum month;

    public MeterReadingId(MeterEntity meterEntity, MonthEnum month) {
        this.meterEntity = meterEntity;
        this.month = month;
    }

    public MeterReadingId() {

    }
}
