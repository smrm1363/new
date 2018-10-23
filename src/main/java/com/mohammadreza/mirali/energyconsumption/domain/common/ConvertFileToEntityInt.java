package com.mohammadreza.mirali.energyconsumption.domain.common;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * This is the responsible for converting files (Multipart file from REST web service or a file from local path in computer).
 *  All service beans (the beans which are responsible for business logic involve CRUD operations) should implement this interface, if operations with file are needed.
 *
 */
public interface ConvertFileToEntityInt {
    /**
     * This is an abstract method which should be implemented the logic of how a DTO file converts to equivalent entity, and call the save logic.
     * @param dtoList is a list of DTO objects
     * @throws IOException
     * @throws ValidationException is the probable exception dou to our business logic
     */
    public void convertToEntity(List dtoList) throws IOException, ValidationException;

    /**
     *
     * @return mapping the columns from DTO to entity
     */
    public Map<String, String> getColumnMapping();


    /**
     * This is the responsible for converting a MultipartFile (generally imported from REST) to a File
     * @param file is the MultipartFile file
     * @return a regular file
     * @throws IOException is the probable exception dou to our business logic
     */
    default File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    /**
     * This is the responsible for uploading a MultipartFile (generally imported from REST)
     * @param multipartFile
     * @param t is the Class of destination entity
     * @param <T> is the type of destination entity
     * @throws IOException
     * @throws ValidationException is the probable exception dou to our business logic
     */
    public default <T> void uploadMultiPartFile(MultipartFile multipartFile,Class<T> t) throws IOException, ValidationException {
        File file = convertMultiPartToFile(multipartFile);
        uploadFile(file,t);


        }

    /**
     * This method is the responsible for uploading a legacy file from local directory in to our entities.
     * If the file was completely correct, it would be deleted, otherwise a LOG file generated in the directory.
     * @param filePath is the local directory path of the file
     * @param t is the Class of destination entity
     * @param <T> is the type of destination entity
     * @throws IOException
     * @throws ValidationException is the probable exception dou to our business logic
     */
    public default <T> void uploadOldFile(String filePath,Class<T> t) throws IOException, ValidationException {
        File file = new File(filePath);
        try{
            uploadFile(file,t);
        }
        catch (ValidationException e)
        {
            /**
             * Write Error log file
             */
            Instant instant = Instant.now();
            long timeStampSeconds = instant.getEpochSecond();
            Path path = Paths.get(file.getParent()+"/"+timeStampSeconds+".log");
            //Use try-with-resource to get auto-closeable writer instance
            try (BufferedWriter writer = Files.newBufferedWriter(path))
            {
                writer.write(e.getMessage());
            }
            throw e;
        }
        /**
         * File processed without any problem, thus delete the file
         */
        file.delete();

    }

    /**
     * This is the responsible for convert a file to an equivalent DTO and call the abstract  convertToEntity
     * @param file is the inputted file
     * @param t is the Class of the DTO
     * @param <T> is the Type of the DTO
     * @throws IOException
     * @throws ValidationException is the probable exception dou to our business logic
     */
    public default <T> void uploadFile(File file,Class<T> t) throws IOException, ValidationException {


        List<T> dtoList = convertFileToDto(new FileReader(file),t);
        convertToEntity(dtoList);


    }

    /**
     * This is the responsible for converting the inputed file to a List of DTO
     * @param reader is the inputted file
     * @param t is the Class of the DTO
     * @param <T> is the Type of the DTO
     * @return a list of DTO
     * @throws IOException
     */
    public default <T> List<T> convertFileToDto(Reader reader,Class<T> t) throws IOException {
        List<T> dtoList;
/**
 * CsvToBean is an library to converting CSV files to Object
 */
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader).withType(t)
                    .withIgnoreLeadingWhiteSpace(true).build();


            HeaderColumnNameTranslateMappingStrategy<T> strategy =
                    new HeaderColumnNameTranslateMappingStrategy<T>();
            strategy.setType((Class<? extends T>) t);
            strategy.setColumnMapping(getColumnMapping());

            csvToBean.setMappingStrategy(strategy);
            dtoList = csvToBean.parse();


        return dtoList;

    }


}