package com.mohammadreza.mirali.energyconsumption.domain.profile;

import com.mohammadreza.mirali.energyconsumption.domain.TestCaseData;
import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;
import com.mohammadreza.mirali.energyconsumption.domain.common.RepositoryCompletion;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by mmirali on 21/10/2018.
 */
public class ProfileFractionServiceTest {

    @Mock
    private  ProfileRepository profileRepository;
    @Mock
    private RepositoryCompletion repositoryCompletion;
    @Mock
    private MeterRepository meterRepository;

    @Test
    public void insertProfile() throws Exception {

        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        ProfileFractionService spy = Mockito.spy(profileFractionService);
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntityList.add(TestCaseData.getPreperedProfile());
        doNothing().when(spy).saveProfileList(profileEntityList);
        spy.insertProfile(TestCaseData.getPreperedProfile());

    }

    @Test
    public void convertToEntity() throws Exception {
        ProfileFractionService profileFractionService = mock(ProfileFractionService.class);
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntityList.add(TestCaseData.getPreperedProfile());
        when(profileFractionService.getEntityListFromDtoList(TestCaseData.getPreperedProfileFractionDto())).thenReturn((profileEntityList));
        profileFractionService.convertToEntity(TestCaseData.getPreperedProfileFractionDto());


    }

    @Test
    public void getEntityListFromDtoList() throws Exception {


        profileRepository = mock(ProfileRepository.class);
        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        when(profileRepository.findById(TestCaseData.getPreperedProfile().getId())).thenReturn(java.util.Optional.ofNullable(TestCaseData.getPreperedProfile()));
        List<ProfileEntity> profileEntityList = profileFractionService.getEntityListFromDtoList(TestCaseData.getPreperedProfileFractionDto());
        assertTrue(profileEntityList.size()>0 );
    }

    @Test
    public void deleteProfile() throws Exception {
        profileRepository = mock(ProfileRepository.class);
        meterRepository = mock(MeterRepository.class);
        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        doNothing().when(profileRepository).deleteById(TestCaseData.getPreperedProfile().getId());
        when(meterRepository.findById(any())).thenReturn(null);
        profileFractionService.deleteProfile(TestCaseData.getPreperedProfile().getId());
    }

    @Test
    public void findProfileById() throws Exception {
        profileRepository = mock(ProfileRepository.class);
        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        when(profileRepository.findById(TestCaseData.getPreperedProfile().getId())).thenReturn(java.util.Optional.ofNullable(TestCaseData.getPreperedProfile()));
        ProfileEntity result = profileFractionService.findProfileById(TestCaseData.getPreperedProfile().getId());
        assertTrue(result.equals(TestCaseData.getPreperedProfile()));
    }

    @Test
    public void saveProfileList() throws Exception {
        repositoryCompletion = mock(RepositoryCompletion.class);
        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntityList.add(TestCaseData.getPreperedProfile());
        doNothing().when(repositoryCompletion).saveEntityListWithValidation(profileEntityList,meterRepository,"profile.fraction.validation");
        profileFractionService.saveProfileList(profileEntityList);
    }

    @Test
    public void getColumnMapping() throws Exception {

        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        Map<String,String> columnMapping = profileFractionService.getColumnMapping();
        assertTrue(columnMapping.get("Month").equals("month"));
        assertTrue(columnMapping.get("Fraction").equals("fraction"));
    }




}