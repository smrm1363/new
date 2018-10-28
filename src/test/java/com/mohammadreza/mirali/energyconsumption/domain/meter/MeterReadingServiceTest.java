package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.TestCaseData;
import com.mohammadreza.mirali.energyconsumption.domain.common.RepositoryCompletion;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MeterReadingServiceTest {


    private MeterReadingService meterReadingService;
    @Mock
    private MeterRepository meterRepository;
    @Mock
    private MeterReadingRepository meterReadingRepository;
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private RepositoryCompletion repositoryCompletion;


    public MeterReadingServiceTest() {
    }


    @Test
    public void insertMeter() throws Exception {

        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        MeterReadingService spy = spy(meterReadingService);
        List<MeterEntity> meterEntityList = new ArrayList<>();
        meterEntityList.add(TestCaseData.getPreperedMeter());
        meterReadingService.insertMeter(TestCaseData.getPreperedMeter());

    }

    @Test
    public void convertToEntity() throws Exception {
        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        MeterReadingService spy = spy(meterReadingService);
        when(spy.getEntityListFromDtoList(anyList())).thenReturn(new ArrayList<>());
        doNothing().when(spy).saveMeterList(anyList());
         spy.convertToEntity(new ArrayList());
    }


@Test
    public void getEntityListFromDtoList() throws Exception{

        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        when(meterRepository.findById(TestCaseData.getPreperedMeter().getId())).thenReturn(java.util.Optional.ofNullable(TestCaseData.getPreperedMeter()));
        when(profileRepository.findById(TestCaseData.getPreperedMeter().getProfileEntity().getId())).thenReturn(java.util.Optional.ofNullable(TestCaseData.getPreperedProfile()));
        List<MeterEntity> meterEntities = meterReadingService.getEntityListFromDtoList(TestCaseData.getPreperedDtoMeterReading());
        assertTrue(meterEntities.size()>0 );

    }

    @Test
    public void deleteMeter() throws Exception {

        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        doNothing().when(meterRepository).deleteById(TestCaseData.getPreperedMeter().getId());
        meterReadingService.deleteMeter(TestCaseData.getPreperedMeter().getId());
    }

    @Test
    public void findMeterById() throws Exception {

        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        when(meterRepository.findById(TestCaseData.getPreperedMeter().getId())).thenReturn(java.util.Optional.ofNullable(TestCaseData.getPreperedMeter()));
        MeterEntity result = meterReadingService.findMeterById(TestCaseData.getPreperedMeter().getId());
        assertTrue(result.equals(TestCaseData.getPreperedMeter()));
    }

    @Test
    public void loadConsumption() throws Exception {

        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        List<MeterReadingEntity> meterReadingEntityList = TestCaseData.getPreperedMeter().getMeterReadingEntityList();
        when(meterReadingRepository.findByMeterEntityIdAndMonth(TestCaseData.getPreperedMeter().getId(),TestCaseData.getPreperedMeter().getMeterReadingEntityList().get(0).getMonth()))
                .thenReturn(meterReadingEntityList);
        Double result = meterReadingService.loadConsumption(TestCaseData.getPreperedMeter().getId(),TestCaseData.getPreperedMeter().getMeterReadingEntityList().get(0).getMonth().toString());
        assertTrue(result.equals(10.0));
    }

    @Test
    public void saveMeterList() throws Exception {

        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        List<MeterEntity> meterEntityList = new ArrayList<>();
        meterEntityList.add(TestCaseData.getPreperedMeter());
        meterReadingService.saveMeterList(meterEntityList);

    }

    @Test
    public void getColumnMapping() throws Exception {

        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        Map<String,String> columnMapping = meterReadingService.getColumnMapping();
        assertTrue(columnMapping.get("MeterID").equals("meterID"));
        assertTrue(columnMapping.get("Meter reading").equals("meterReading"));
    }

}