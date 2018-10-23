package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.TestCaseData;
import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeterReadingServiceIntegrationTest {
    @Autowired
    MeterReadingService meterReadingService;
    @Autowired
    ProfileFractionService profileFractionService;

    @Before
    public void init() throws ValidationException {
        profileFractionService.insertProfile(TestCaseData.getPreperedProfile());
    }
    @Test
    public void crudMeter() throws Exception {
        meterReadingService.insertMeter(TestCaseData.getPreperedMeterSorted());
        MeterEntity meterEntity = meterReadingService.findMeterById(TestCaseData.getPreperedMeter().getId());
        assertTrue(meterEntity.getId().equals(TestCaseData.getPreperedMeter().getId()));
        Double consumtion = meterReadingService.loadConsumption(meterEntity.getId(),"JAN");
        assertEquals(consumtion,TestCaseData.getPreperedMeter().getMeterReadingEntityList().get(0).getReadedMeter());
        meterReadingService.deleteMeter(meterEntity.getId());
        meterEntity = meterReadingService.findMeterById(TestCaseData.getPreperedMeter().getId());
        assertNull(meterEntity);
    }

    @Test
    @Transactional
    public void convertToEntity() throws Exception {

        meterReadingService.convertToEntity(TestCaseData.getPreperedDtoMeterReading());
        MeterEntity meterEntity = meterReadingService.findMeterById(TestCaseData.getPreperedMeter().getId());
        assertNotNull(meterEntity);
    }


    @Test
    public void saveMeterList() throws Exception {
        List<MeterEntity> meterEntityList = new ArrayList<>();
        meterEntityList.add(TestCaseData.getPreperedMeterSorted());
        meterReadingService.saveMeterList(meterEntityList);
        MeterEntity meterEntity = meterReadingService.findMeterById(TestCaseData.getPreperedMeter().getId());
        assertNotNull(meterEntity);
    }


}