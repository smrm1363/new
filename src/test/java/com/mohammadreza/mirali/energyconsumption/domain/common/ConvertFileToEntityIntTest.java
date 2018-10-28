package com.mohammadreza.mirali.energyconsumption.domain.common;

import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionDto;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by mmirali on 20/10/2018.
 */

public class ConvertFileToEntityIntTest {


    @Test
    public void convertMultiPartToFile() throws Exception {
        ConvertFileToEntityInt testedClass = Mockito.mock(ConvertFileToEntityInt.class, Mockito.CALLS_REAL_METHODS);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("test-file", "test.csv", "text/plain", "test data".getBytes());
        File file = testedClass.convertMultiPartToFile(mockMultipartFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        assertTrue(br.readLine().equals("test data"));
    }


    @Test
    public void convertFileToDto() throws Exception {

        ConvertFileToEntityInt testedClass = Mockito.mock(ConvertFileToEntityInt.class, Mockito.CALLS_REAL_METHODS);

        StringReader reader = new StringReader("Month,Profile,Fraction\n" +
                "JAN,A,0.2\n" +
                "JAN,B,0.18\n" +
                "MAR,A,0.0");


        List<ProfileFractionDto> list = testedClass.convertFileToDto(reader, ProfileFractionDto.class);
        assertTrue(list.size() == 3);


    }

}