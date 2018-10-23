package com.mohammadreza.mirali.energyconsumption;

import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterEntity;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterReadingDto;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@RestController

public class MeterRestController {

    /**
     * The business logic for Meter and MeterReading
     */
    private final MeterReadingService meterReadingService;

    @Autowired
    public MeterRestController( MeterReadingService meterReadingService) {

        this.meterReadingService = meterReadingService;
    }

    /**
     * This endpoint is for uploading MeterReading CSV file. It saves and updates the content of CSV in to the database
     * @param multiPartFile is the CSV file
     * @throws IOException if the file has problem
     * @throws ValidationException is the probable exception dou to our business logic
     */
    @RequestMapping(value = "/uploadMeterReading", method = RequestMethod.POST)
    public void uploadMeterReading(@RequestPart(value = "file") MultipartFile multiPartFile) throws IOException, ValidationException {

        Optional<String> fileExtention = MyUtil.getExtensionByStringHandling(multiPartFile.getOriginalFilename());
        if(fileExtention.isPresent()) {
            if (fileExtention.get().equalsIgnoreCase("CSV")) {
                meterReadingService.uploadMultiPartFile(multiPartFile, MeterReadingDto.class);
                return;
            }
        }
        throw new IOException("File extention is not CSV");

    }

    /**
     * This endpoit load and return Consumption of a Meter in a month
     * @param meterId
     * @param month
     * @return
     */
    @RequestMapping(value = "/loadConsumption", method = RequestMethod.GET)
    public Double loadConsumption(String meterId,String month)
    {
        return meterReadingService.loadConsumption(meterId,month);
    }

    /**
     * This endpoint is for insert and update a Meter. The input should be JSON data
     * @param meter is the inputted JSON object
     * @throws ValidationException s the probable exception dou to our business logic
     */
    @RequestMapping(value = "/insertMeter", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertMeter(@RequestBody MeterEntity meter) throws ValidationException {
         meterReadingService.insertMeter(meter);
    }

    /**
     * This endpoint get a CSV file from local directory.
     * @param localFilePath is the path of the file
     * @throws IOException
     * @throws ValidationException is the probable exception dou to our business logic
     */
    @RequestMapping(value = "/insertMeterFromLocalFile", method = RequestMethod.POST)
    public void insertMeterFromLocalFile(@RequestPart(value = "localFilePath") String localFilePath) throws IOException, ValidationException {

        Optional<String> fileExtention = MyUtil.getExtensionByStringHandling(localFilePath);
        if(fileExtention.isPresent())
        {
            if(fileExtention.get().equalsIgnoreCase("CSV"))
            {
                meterReadingService.uploadOldFile(localFilePath, MeterReadingDto.class);
                return;
            }
        }
        throw new IOException("File extention in this path is not CSV");

    }

    /**
     *This endpoint delete a Meter and it't MeterReadings from Our Database
     * @param meterId
     */
    @RequestMapping(value = "/deleteMeter", method = RequestMethod.DELETE)
    public void deleteMeter(String meterId)
    {
        meterReadingService.deleteMeter(meterId);
    }

    /**
     * his end point find and return a Meter and it's MeterReadings from our Database
     * @param meterId
     * @return
     */
    @RequestMapping(value = "/findMeter", method = RequestMethod.GET)
    public MeterEntity findMeter(String meterId)
    {
       return meterReadingService.findMeterById(meterId);
    }




}
