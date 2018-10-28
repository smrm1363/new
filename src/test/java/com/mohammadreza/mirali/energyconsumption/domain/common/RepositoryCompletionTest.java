package com.mohammadreza.mirali.energyconsumption.domain.common;

import com.mohammadreza.mirali.energyconsumption.domain.TestCaseData;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileRepository;
import com.mohammadreza.mirali.energyconsumption.domain.profile.validation.SumFractionValidation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class RepositoryCompletionTest {
    @Mock
    private ValidationsFactory validationsFactory;
    @Mock
    private ProfileRepository profileRepository;
    @Test
    public void saveEntityListWithValidation() throws Exception {


        RepositoryCompletion repositoryCompletion = new RepositoryCompletion(validationsFactory);
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntityList.add(TestCaseData.getPreperedProfile());
        ProfileRepository profileRepository = mock(ProfileRepository.class);
        List<ValidationRule> validationRuleList = new ArrayList<>();
        validationRuleList.add(new SumFractionValidation());
        when(validationsFactory.getValidationRulesByPropertyName(anyString())).thenReturn(validationRuleList);
        when(profileRepository.saveAll(any())).thenReturn(profileEntityList);
        repositoryCompletion.saveEntityListWithValidation(profileEntityList,profileRepository,"");
    }

}