package com.mohammadreza.mirali.energyconsumption.domain.meter;

/**
 * The equivalent DTO for the inputted MeterReading data
 */
public class MeterReadingDto {
    String meterID;
    String profile;
    String month;
    Double meterReading;

    public MeterReadingDto(String meterID, String profile, String month, Double meterReading) {
        this.meterID = meterID;
        this.profile = profile;
        this.month = month;
        this.meterReading = meterReading;
    }

    public MeterReadingDto() {

    }

    public String getMeterID() {
        return meterID;
    }

    public void setMeterID(String meterID) {
        this.meterID = meterID;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(Double meterReading) {
        this.meterReading = meterReading;
    }
}
