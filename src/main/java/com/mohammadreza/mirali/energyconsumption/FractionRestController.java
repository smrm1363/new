package com.mohammadreza.mirali.energyconsumption;

import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionDto;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController

public class FractionRestController {

    /**
     * The business logic for Profile and ProfileFraction
     */
    private final ProfileFractionService profileFractionService;


    @Autowired
    public FractionRestController(ProfileFractionService profileFractionService) {
        this.profileFractionService = profileFractionService;

    }

    /**
     * This endpoint is for uploading ProfileFraction CSV file. It saves and updates the content of CSV in to the database
     * @param multiPartFile is the CSV file
     * @throws IOException if the file has problem
     * @throws ValidationException is the probable exception dou to our business logic
     */
    @RequestMapping(value = "/uploadProfileFraction", method = RequestMethod.POST)
    public void uploadProfileFraction(@RequestPart(value = "file") MultipartFile multiPartFile) throws IOException, ValidationException {
        Optional<String> fileExtention = MyUtil.getExtensionByStringHandling(multiPartFile.getOriginalFilename());
        if(fileExtention.isPresent())
        {
            if(fileExtention.get().equalsIgnoreCase("CSV"))
            {
                profileFractionService.uploadMultiPartFile(multiPartFile, ProfileFractionDto.class);
                return;
            }

        }
        throw new IOException("File extention is not CSV");


    }

    /**
     * This endpoint is for insert and update a Profile. The input should be JSON data
     * @param profile is the inputted JSON object
     * @throws ValidationException is the probable exception dou to our business logic
     */
    @RequestMapping(value = "/insertProfile", method = RequestMethod.POST)
    public void insertProfile(@RequestBody ProfileEntity profile) throws ValidationException {
         profileFractionService.insertProfile(profile);
    }

    /**
     * This endpoint get a CSV file from local directory.
     * @param localFilePath is the path of the file
     * @throws IOException
     * @throws ValidationException is the probable exception dou to our business logic
     */
    @RequestMapping(value = "/insertProfileFromLocalFile", method = RequestMethod.POST)
    public void insertProfileFromLocalFile(@RequestPart(value = "localFilePath") String localFilePath) throws IOException, ValidationException {

        Optional<String> fileExtention = MyUtil.getExtensionByStringHandling(localFilePath);
        if(fileExtention.isPresent())
        {
            if(fileExtention.get().equalsIgnoreCase("CSV"))
            {
                profileFractionService.uploadOldFile(localFilePath, ProfileFractionDto.class);
                return;
            }
        }
        throw new IOException("File extention in this path is not CSV");

    }

    /**
     * This endpoint delete a Profile and it't fraction from Our Database
     * @param profileId
     * @throws ValidationException
     */
    @RequestMapping(value = "/deleteProfile", method = RequestMethod.DELETE)
    public void deleteProfile(String profileId) throws ValidationException {
        profileFractionService.deleteProfile(profileId);
    }

    /**
     * This end point find and return a Profile and it's fractions from our Database
     * @param profileId
     * @return
     */
    @RequestMapping(value = "/findProfile", method = RequestMethod.GET)
    public ProfileEntity findProfile(String profileId)
    {
        return profileFractionService.findProfileById(profileId);
    }




}
