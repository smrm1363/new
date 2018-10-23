# Energy consumption
In this sample project I used below technologies and tools:

Java 8
 
Spring Boot

JPA - Hibernate

Spring data

H2 database : beacuse it is just a demo

Spring REST

Spring IOC

opencsv : for converting CSV files to DTO

Junit,

Mockito,

Spring integration test

MAVEN

Embedeb spring boot TomeCat.

About the implemented project, First of all, I would like to mention that I developed both the main task and the Bonus task are mentioned in the use case. Second, I assume all the mentioned assumptions in the use case are fulfilled, and I did not check them. For more explanation, I have developed a dynamic solution for checking validations, I mean we can add new validation by just developing a new validation logic in an inherited class from my ValidationRule, then add the class reference into the application.properties file, this is a way to have a chain of validations.

I used some useful design patterns such as Factory and Strategy, moreover, I tried to make my codes reusable.
 In this project, I mad different type of classes such as entities, Repositories, Services(the main business logic), Rest controllers, DTO, Util, useful Component and interfaces. Furthermore, I mad some important unit tests and integration tests, indeed, I mad integration tests only for service beans, due to they work with all other classes and database.

As it is mentioned in the use case, we have two CSV files which are the legacy format, I have changed it into a new design in as JPA entities.
 The new  entities are ProfileEntity, ProfileFractionEntity, MeterEntity, MeterReadingEntity. 
 The ProfileEntity has one-to-many relation to ProfileFractionEntity, for instance, we have one profile 'A' which has a list of fractions for 12 months.
  The MeterEntity has one-to-many relation with MeterReadingEntity, and a many-to-one relation with the ProfileEntity, it means a profile could have many Meter, and a Meter could have many MeterReading for instance for 12 months.
    
In the implemented REST APIs, we have the wanted CRUD operations and the bonus task.
 For inserting and updating data via CSV files, I have implemented 'uploadProfileFraction', and 'uploadMeterReading' while the input parameters for them are the CSV file in the body of a POST method. 
 Also, we have 'insertProfileFromLocalFile', and 'insertMeterFromLocalFile' which read CSV file from a local directory (for the bonus task). All of these APIs are for the legacy format.
          
Moreover, for inserting and updating in new format I have implemented 'insertProfile', and 'insertMeter' while the input data format should be JSON. The structures of the JSON objects are similar to the structure of our entities(please follow the JSON format of bellow sample).
For delete operations I have developed 'deleteProfile', and 'deleteMeter'. For finding we have 'findProfile', and 'findMeter'. Finally to retrieve Consumption for a given month for a given Meter we have 'loadConsumption' API.           

According to the context of the use case the REST APIs are described below:
git 


http://localhost:8080/uploadProfileFraction  Method  : POST     Parameter:  add CSV file in body     KEY: file   VALUE: The CSV file for ProfileFraction

http://localhost:8080/deleteProfile          Method  : DELETE   parameter:  in URL   KEY: profileId  Value: profile id  example: A

http://localhost:8080/findProfile            Method  : GET      parameter:  in URL  KEY: profileId   Value: profile id  example: A

http://localhost:8080/insertProfile          Methos  : POST     Parameter:  add JSON object in body  KEY: profile   VALUE: The SON object for ProfileFraction

Sample JSON:

{
    "id": "B",
    "profileFractionEntityList": [
        {
            "month": "JAN",
            "fraction": 0.1
        },
        {
            "month": "FEB",
            "fraction": 0.1
        },
        {
            "month": "APR",
            "fraction": 0
        },
        {
            "month": "MAY",
            "fraction": 0
        },
        {
            "month": "JUN",
            "fraction": 0
        },
        {
            "month": "JUL",
            "fraction": 0
        },
        {
            "month": "AUG",
            "fraction": 0
        },
        {
            "month": "SEP",
            "fraction": 0
        },
        {
            "month": "OCT",
            "fraction": 0
        },
        {
            "month": "NOV",
            "fraction": 0.4
        },
        {
            "month": "DEC",
            "fraction": 0.4
        },
        {
            "month": "MAR",
            "fraction": 0
        }
    ]
}

http://localhost:8080/insertProfileFromLocalFile         Methos  : POST     Parameter:  add String path of the local CSV  KEY: localFilePath   VALUE: a String contains path of local file  example: E:\profile.csv

--


http://localhost:8080/uploadMeterReading  Method  : POST     Parameter:  add CSV file in body     KEY: file   VALUE: The CSV file for MeterReading

http://localhost:8080/deleteMeter          Method  : DELETE   parameter:  in URL   KEY: meterId  Value: profile id  example: 0001

http://localhost:8080/findMeter           Method  : GET      parameter:  in URL  KEY: meterId   Value: profile id   example: 0001

http://localhost:8080/insertMeter      Methos  : POST     Parameter:  add JSON object in body  KEY: meter    VALUE: The JSON object for MeterReading

Sample JSON format : 

{
    "id": "0003",
    "value": 100,
    "profileEntity": "A",
    "meterReadingEntityList": [
        {
            "month": "JAN",
            "readedMeter": 10,
            "consumtion": 10
        },
        {
            "month": "FEB",
            "readedMeter": 20,
            "consumtion": 10
        },
        {
            "month": "MAR",
            "readedMeter": 20,
            "consumtion": 0
        },
        {
            "month": "APR",
            "readedMeter": 20,
            "consumtion": 0
        },
        {
            "month": "MAY",
            "readedMeter": 20,
            "consumtion": 0
        },
        {
            "month": "JUN",
            "readedMeter": 20,
            "consumtion": 0
        },
        {
            "month": "JUL",
            "readedMeter": 20,
            "consumtion": 0
        },
        {
            "month": "AUG",
            "readedMeter": 20,
            "consumtion": 0
        },
        {
            "month": "SEP",
            "readedMeter": 20,
            "consumtion": 0
        },
        {
            "month": "OCT",
            "readedMeter": 20,
            "consumtion": 0
        },
        {
            "month": "NOV",
            "readedMeter": 60,
            "consumtion": 40
        },
        {
            "month": "DEC",
            "readedMeter": 100,
            "consumtion": 40
        }
    ]
}

http://localhost:8080/insertMeterFromLocalFile         Methos  : POST     Parameter:  add String path of the local CSV  KEY: localFilePath   VALUE: a String contains path of local file  example: E:\meter.csv

http://localhost:8080/loadConsumption          Method  : GET      parameter:  in URL  KEY: meterId&month   Value: profile id  and moth  example: meterId=0001&month=DEC

I am thankful for your consideration.


