package com.mohammadreza.mirali.energyconsumption.domain.profile;

import com.mohammadreza.mirali.energyconsumption.domain.common.*;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterEntity;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * This is the main business logic for ProfileFraction, dou to we need some file related operations, I implement ConvertFileToEntityInt.
 */
@Service("ProfileFractionService")
public class ProfileFractionService implements ConvertFileToEntityInt {
    private final String validationsProperyKey = "profile.fraction.validation";
    private final ProfileRepository profileRepository;
    private final RepositoryCompletion repositoryCompletion;
    private final MeterRepository meterRepository;

    @Autowired
    public ProfileFractionService(ProfileRepository profileRepository, RepositoryCompletion repositoryCompletion, MeterRepository meterRepository) {
        this.profileRepository = profileRepository;
        this.repositoryCompletion = repositoryCompletion;
        this.meterRepository = meterRepository;
    }

    /**
     * Insert and update operation for a profileEntity and it's ProfileFraction list
     * @param profileEntity is a meter entity and it's ProfileFraction list
     * @throws ValidationException is the probable exception dou to our business logic
     */
    public void insertProfile(ProfileEntity profileEntity) throws ValidationException {
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntity.getProfileFractionEntityList().forEach(profileFractionEntity -> profileFractionEntity.setProfileEntity(profileEntity));
        profileEntityList.add(profileEntity);
        saveProfileList(profileEntityList);
    }

    /**
     * It gets entity list from DTO list and call the save operation
     * @param dtoList is a list of DTO objects
     * @throws IOException
     * @throws ValidationException
     */
    @Override
    public void convertToEntity(List dtoList) throws IOException, ValidationException {


     saveProfileList(getEntityListFromDtoList(dtoList));
    }

    /**
     * It convert a MeterReading DTO to list of ProfileEntity and call save operation
     * @param dtoList
     * @return
     */
    public List<ProfileEntity> getEntityListFromDtoList(List dtoList)
    {
        List<ProfileFractionDto> profileFractionDtoList = dtoList;
        Map<String,ProfileEntity> profileEntityMap = new HashMap<>();
        profileFractionDtoList.forEach(profileFractionDto ->
        {
            ProfileEntity profileEntity = profileEntityMap.get(profileFractionDto.getProfile());

            if(profileEntity == null) {
                Optional<ProfileEntity> profileEntityOptional = profileRepository.findById(profileFractionDto.getProfile());
                if (profileEntityOptional.isPresent()) {
                    profileEntity = profileEntityOptional.get();
                } else {
                    profileEntity = new ProfileEntity();
                    profileEntity.setId(profileFractionDto.getProfile());
                }
            }


            if(profileEntity.getProfileFractionEntityList()==null)
            {
                profileEntity.setProfileFractionEntityList(new ArrayList<ProfileFractionEntity>());
            }

            Boolean updated = false;
            for(ProfileFractionEntity profileFractionEntity1:profileEntity.getProfileFractionEntityList())
            {
                if(profileFractionEntity1.getProfileEntity().getId().equals(profileFractionDto.getProfile()))
                    if(profileFractionEntity1.getMonth() == MonthEnum.valueOf(profileFractionDto.getMonth()))
                    {
                        profileFractionEntity1.setFraction(profileFractionDto.getFraction());
                        updated = true;
                        break;
                    }
            }
            if(!updated)
            {
                ProfileFractionEntity profileFractionEntity = new ProfileFractionEntity();
                profileFractionEntity.setMonth(MonthEnum.valueOf(profileFractionDto.getMonth()));
                profileFractionEntity.setProfileEntity(profileEntity);
                profileFractionEntity.setFraction(profileFractionDto.getFraction());
                profileEntity.getProfileFractionEntityList().add(profileFractionEntity);
            }


            profileEntityMap.put(profileEntity.getId(),profileEntity);

        });
        return new ArrayList<>(profileEntityMap.values());
    }

    /**
     * This method calls delete operation from ProfileRepository.
     * If a profile is used in a Meter, it return an ValidationException.
     * @param profileID
     * @throws ValidationException
     */
    public void deleteProfile(String profileID) throws ValidationException {

        List<MeterEntity> meterEntityList= meterRepository.findByProfileEntityId(profileID);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("This profile with ("+profileID+") is used in one or some Meters, first delete the meters. List of meeters : ");
        meterEntityList.forEach(meterEntity -> {
            stringBuffer.append(meterEntity.getId()+"; ");
        });
        if(meterEntityList.size()>0)
        {
            throw new ValidationException(stringBuffer.toString());
        }
        profileRepository.deleteById(profileID);

    }

    /**
     * This method find an ProfileEntity by ID
     * @param profileId
     * @return
     */
    public ProfileEntity findProfileById(String profileId)
    {
        Optional<ProfileEntity> profileEntityOptional = profileRepository.findById(profileId);
        if (profileEntityOptional.isPresent())
        return profileEntityOptional.get();
        return null;
    }


    /**
     * This method is for saving a list of a ProfileEntity and it's ProfileFraction list, inside the repositoryCompletion the validations will be checked
     * @param profileEntityList
     * @throws ValidationException
     */
    public void saveProfileList(List<ProfileEntity> profileEntityList) throws ValidationException {

        repositoryCompletion.saveEntityListWithValidation(profileEntityList,profileRepository,validationsProperyKey);
    }

    /**
     *
     * @return mapping the columns from DTO to entity
     */
    @Override
    public Map<String, String> getColumnMapping() {
        Map<String, String> columnMapping = new HashMap<String, String>();
        columnMapping.put("Month", "month");
        columnMapping.put("Profile", "profile");
        columnMapping.put("Fraction", "fraction");

        return columnMapping;
    }



}
