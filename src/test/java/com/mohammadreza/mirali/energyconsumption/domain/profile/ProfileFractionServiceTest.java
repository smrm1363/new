package com.mohammadreza.mirali.energyconsumption.domain.profile;

import com.mohammadreza.mirali.energyconsumption.domain.TestCaseData;
import com.mohammadreza.mirali.energyconsumption.domain.common.RepositoryCompletion;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by mmirali on 21/10/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileFractionServiceTest {

    @Mock
    private  ProfileRepository profileRepository;
    @Mock
    private RepositoryCompletion repositoryCompletion;
    @Mock
    private MeterRepository meterRepository;
    @Mock
    private ProfileFractionService profileFractionService;

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

        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntityList.add(TestCaseData.getPreperedProfile());
        profileFractionService.convertToEntity(TestCaseData.getPreperedProfileFractionDto());


    }

    @Test
    public void getEntityListFromDtoList() throws Exception {


        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        when(profileRepository.findById(TestCaseData.getPreperedProfile().getId())).thenReturn(java.util.Optional.ofNullable(TestCaseData.getPreperedProfile()));
        List<ProfileEntity> profileEntityList = profileFractionService.getEntityListFromDtoList(TestCaseData.getPreperedProfileFractionDto());
        assertTrue(profileEntityList.size()>0 );
    }

    @Test
    public void deleteProfile() throws Exception {

        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        doNothing().when(profileRepository).deleteById(TestCaseData.getPreperedProfile().getId());
        profileFractionService.deleteProfile(TestCaseData.getPreperedProfile().getId());
    }

    @Test
    public void findProfileById() throws Exception {

        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        when(profileRepository.findById(TestCaseData.getPreperedProfile().getId())).thenReturn(java.util.Optional.ofNullable(TestCaseData.getPreperedProfile()));
        ProfileEntity result = profileFractionService.findProfileById(TestCaseData.getPreperedProfile().getId());
        assertTrue(result.equals(TestCaseData.getPreperedProfile()));
    }

    @Test
    public void saveProfileList() throws Exception {
        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntityList.add(TestCaseData.getPreperedProfile());
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