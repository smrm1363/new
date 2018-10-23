package com.mohammadreza.mirali.energyconsumption.domain.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ValidationsFactoryTest {

    @Test
    public void getValidationRulesByPropertyName() throws Exception {

        Environment env= mock(Environment.class);
        when(env.getProperty(anyString())).thenReturn("com.mohammadreza.mirali.energyconsumption.domain.meter.validation.FractionShoudBeFound,com.mohammadreza.mirali.energyconsumption.domain.meter.validation.MeterShouldBeLowerThanPrev");
        ValidationsFactory validationsFactory = new ValidationsFactory(env);
        List<ValidationRule> rules= validationsFactory.getValidationRulesByPropertyName("meter.reading.validations");
        assertTrue(rules.get(0).getClass().getName().equals("com.mohammadreza.mirali.energyconsumption.domain.meter.validation.FractionShoudBeFound"));
        assertTrue(rules.get(1).getClass().getName().equals("com.mohammadreza.mirali.energyconsumption.domain.meter.validation.MeterShouldBeLowerThanPrev"));

    }

}