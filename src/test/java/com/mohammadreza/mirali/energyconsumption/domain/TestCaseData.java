package com.mohammadreza.mirali.energyconsumption.domain;

import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterEntity;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterReadingDto;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterReadingEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionDto;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestCaseData {

    public static MeterEntity getPreperedMeter()
    {
        MeterEntity meterEntity = new MeterEntity();

        meterEntity.setId("001");
        meterEntity.setProfileEntity(getPreperedProfile());
        MeterReadingEntity meterReadingEntity1 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,11.0);
        MeterReadingEntity meterReadingEntity2 = new MeterReadingEntity(meterEntity, MonthEnum.FEB,19.0);
        MeterReadingEntity meterReadingEntity3 = new MeterReadingEntity(meterEntity, MonthEnum.MAR,30.0);
        MeterReadingEntity meterReadingEntity4 = new MeterReadingEntity(meterEntity, MonthEnum.APR,40.0);
        MeterReadingEntity meterReadingEntity5 = new MeterReadingEntity(meterEntity, MonthEnum.AUG,70.0);
        MeterReadingEntity meterReadingEntity6 = new MeterReadingEntity(meterEntity, MonthEnum.DEC,100.0);
        MeterReadingEntity meterReadingEntity7 = new MeterReadingEntity(meterEntity, MonthEnum.JUL,60.0);
        MeterReadingEntity meterReadingEntity8 = new MeterReadingEntity(meterEntity, MonthEnum.MAY,50.0);
        MeterReadingEntity meterReadingEntity9 = new MeterReadingEntity(meterEntity, MonthEnum.NOV,90.0);
        MeterReadingEntity meterReadingEntity10 = new MeterReadingEntity(meterEntity, MonthEnum.SEP,80.0);
        MeterReadingEntity meterReadingEntity11 = new MeterReadingEntity(meterEntity, MonthEnum.OCT,80.0);
        MeterReadingEntity meterReadingEntity12 = new MeterReadingEntity(meterEntity, MonthEnum.JUN,50.0);
        meterReadingEntity1.setConsumtion(10.0);
        meterEntity.setMeterReadingEntityList(new ArrayList<>());
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity1);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity2);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity3);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity4);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity5);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity6);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity7);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity8);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity9);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity10);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity11);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity12);
        return meterEntity;
    }
    public static List getPreperedDtoMeterReading()
    {
        List<MeterReadingDto> meterReadingDtoList = new ArrayList<>();
        meterReadingDtoList.add(new MeterReadingDto("001","A","JAN",11.0));
        meterReadingDtoList.add(new MeterReadingDto("001","A","FEB",19.0));
        meterReadingDtoList.add(new MeterReadingDto("001","A","MAR",30.0));
        meterReadingDtoList.add(new MeterReadingDto("001","A","APR",40.0));
        meterReadingDtoList.add(new MeterReadingDto("001","A","AUG",70.0));
        meterReadingDtoList.add(new MeterReadingDto("001","A","DEC",100.0));
        meterReadingDtoList.add(new MeterReadingDto("001","A","JUL",60.0));
        meterReadingDtoList.add(new MeterReadingDto("001","A","MAY",50.0));
        meterReadingDtoList.add(new MeterReadingDto("001","A","NOV",90.0));
        meterReadingDtoList.add(new MeterReadingDto("001","A","SEP",80.0));
        meterReadingDtoList.add(new MeterReadingDto("001","A","OCT",80.0));
        meterReadingDtoList.add(new MeterReadingDto("001","A","JUN",50.0));

        return meterReadingDtoList;
    }


      public static ProfileEntity getPreperedProfile()
    {
        ProfileEntity profileEntity = new ProfileEntity();

        profileEntity.setId("A");
        ProfileFractionEntity profileFractionEntity1 = new ProfileFractionEntity(profileEntity, MonthEnum.JAN,0.1);
        ProfileFractionEntity profileFractionEntity2 = new ProfileFractionEntity(profileEntity, MonthEnum.FEB,0.1);
        ProfileFractionEntity profileFractionEntity3 = new ProfileFractionEntity(profileEntity, MonthEnum.MAR,0.1);
        ProfileFractionEntity profileFractionEntity4 = new ProfileFractionEntity(profileEntity, MonthEnum.APR,0.1);
        ProfileFractionEntity profileFractionEntity5 = new ProfileFractionEntity(profileEntity, MonthEnum.AUG,0.1);
        ProfileFractionEntity profileFractionEntity6 = new ProfileFractionEntity(profileEntity, MonthEnum.DEC,0.1);
        ProfileFractionEntity profileFractionEntity7 = new ProfileFractionEntity(profileEntity, MonthEnum.JUL,0.1);
        ProfileFractionEntity profileFractionEntity8 = new ProfileFractionEntity(profileEntity, MonthEnum.MAY,0.1);
        ProfileFractionEntity profileFractionEntity9 = new ProfileFractionEntity(profileEntity, MonthEnum.NOV,0.1);
        ProfileFractionEntity profileFractionEntity10 = new ProfileFractionEntity(profileEntity, MonthEnum.SEP,0.1);
        ProfileFractionEntity profileFractionEntity11 = new ProfileFractionEntity(profileEntity, MonthEnum.OCT,0.0);
        ProfileFractionEntity profileFractionEntity12 = new ProfileFractionEntity(profileEntity, MonthEnum.JUN,0.0);
        profileEntity.setProfileFractionEntityList(new ArrayList<>());
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity1);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity2);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity3);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity4);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity5);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity6);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity7);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity8);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity9);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity10);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity11);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity12);
        return profileEntity;
    }

    public static List getPreperedProfileFractionDto()
    {
        List<ProfileFractionDto> meterReadingDtoList = new ArrayList<>();
        meterReadingDtoList.add(new ProfileFractionDto("JAN","A",0.1));
        meterReadingDtoList.add(new ProfileFractionDto("FEB","A",0.1));
        meterReadingDtoList.add(new ProfileFractionDto("MAR","A",0.1));
        meterReadingDtoList.add(new ProfileFractionDto("APR","A",0.1));
        meterReadingDtoList.add(new ProfileFractionDto("MAY","A",0.1));
        meterReadingDtoList.add(new ProfileFractionDto("JUN","A",0.0));
        meterReadingDtoList.add(new ProfileFractionDto("JUL","A",0.1));
        meterReadingDtoList.add(new ProfileFractionDto("AUG","A",0.1));
        meterReadingDtoList.add(new ProfileFractionDto("SEP","A",0.1));
        meterReadingDtoList.add(new ProfileFractionDto("OCT","A",0.0));
        meterReadingDtoList.add(new ProfileFractionDto("NOV","A",0.1));
        meterReadingDtoList.add(new ProfileFractionDto("DEC","A",0.1));


        return meterReadingDtoList;
    }

    public static MeterEntity getPreperedMeterSorted()
    {
        MeterEntity meterEntity = TestCaseData.getPreperedMeter();
        meterEntity.setProfileEntity(TestCaseData.getPreperedProfile());
        List<MeterReadingEntity> meterReadingEntityList = meterEntity.getMeterReadingEntityList().stream()
                .sorted((o1, o2) -> o1.getMonth().compareTo(o2.getMonth())).collect(Collectors.toList());
        meterEntity.setMeterReadingEntityList(meterReadingEntityList);
        meterEntity.setValue(meterReadingEntityList.get(meterReadingEntityList.size()-1).getReadedMeter());
        return meterEntity;
    }
}
