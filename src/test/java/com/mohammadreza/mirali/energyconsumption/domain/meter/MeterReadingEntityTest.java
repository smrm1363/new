package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mmirali on 21/10/2018.
 */
public class MeterReadingEntityTest {
    @Test
    public void calculateConsumption() throws Exception {

        MeterEntity meterEntity = new MeterEntity();
        meterEntity.setId("001");
        MeterReadingEntity meterReadingEntity1 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,10.0);
        MeterReadingEntity meterReadingEntity2 = new MeterReadingEntity(meterEntity, MonthEnum.FEB,20.0);
        MeterReadingEntity meterReadingEntity3 = new MeterReadingEntity(meterEntity, MonthEnum.MAR,40.0);
        List<MeterReadingEntity> meterReadingEntityList = new ArrayList<>();
        meterReadingEntityList.add(meterReadingEntity1);
        meterReadingEntityList.add(meterReadingEntity2);
        meterReadingEntityList.add(meterReadingEntity3);
        meterEntity.setMeterReadingEntityList(meterReadingEntityList);

        assertTrue(meterReadingEntity1.calculateConsumption().equals(10.0));
        assertTrue(meterReadingEntity3.calculateConsumption().equals(20.0));

    }

}