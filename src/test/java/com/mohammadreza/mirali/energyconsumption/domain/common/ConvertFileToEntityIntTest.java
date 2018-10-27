package com.mohammadreza.mirali.energyconsumption.domain.common;

import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionDto;
import com.opencsv.bean.CsvToBean;
import org.assertj.core.util.Files;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by mmirali on 20/10/2018.
 */

public class ConvertFileToEntityIntTest {




    @Test
    public void convertMultiPartToFile() throws Exception {
        ConvertFileToEntityInt testedClass = Mockito.mock(ConvertFileToEntityInt.class,Mockito.CALLS_REAL_METHODS);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("test-file","test.csv","text/plain", "test data".getBytes());
        File file = testedClass.convertMultiPartToFile(mockMultipartFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        assertTrue(br.readLine().equals("test data"));
    }


    @Test
    public void convertFileToDto() throws Exception {

        ConvertFileToEntityInt testedClass = Mockito.mock(ConvertFileToEntityInt.class,Mockito.CALLS_REAL_METHODS);

        StringReader reader = new StringReader("Month,Profile,Fraction\n" +
                "JAN,A,0.2\n" +
                "JAN,B,0.18\n" +
                "MAR,A,0.0");


        List<ProfileFractionDto> list = testedClass.convertFileToDto(reader, ProfileFractionDto.class);
        assertTrue(list.size()==3);



    }

}