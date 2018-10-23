package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionEntity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Entity
@IdClass(MeterReadingId.class)
public class MeterReadingEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    MeterEntity meterEntity;

    @Id
    @NotNull
    @Enumerated(EnumType.STRING)
    private MonthEnum month;

    @NotNull
    @DecimalMin("0.0")
    private Double readedMeter;

    private Double consumtion;

    @Transient
    private ProfileFractionEntity matchedProfileFractionEntity;

    public MeterReadingEntity(@NotNull MeterEntity meterEntity, @NotNull MonthEnum month, @NotNull @DecimalMin("0.0") Double readedMeter) {
        this.meterEntity = meterEntity;
        this.month = month;
        this.readedMeter = readedMeter;
    }

    public MeterReadingEntity() {

    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public MeterEntity getMeterEntity() {
        return meterEntity;
    }

    public void setMeterEntity(MeterEntity meterEntity) {
        this.meterEntity = meterEntity;
    }

    public MonthEnum getMonth() {
        return month;
    }

    public void setMonth(MonthEnum month) {
        this.month = month;
    }

    public Double getReadedMeter() {
        return readedMeter;
    }

    public void setReadedMeter(Double readedMeter) {
        this.readedMeter = readedMeter;
    }

    /**
     * This method calculate Consumption for the current MeterReading
     * @return
     */
    public Double calculateConsumption()
    {
        if(month.equals(MonthEnum.JAN))
            return readedMeter;
        else
        {
             Optional<MeterReadingEntity> meterReadingEntity = meterEntity.getMeterReadingEntityList().stream().filter(meterReadingEntity1 ->
             meterReadingEntity1.getMonth().equals(month.prev())).findFirst();
             return readedMeter - meterReadingEntity.get().getReadedMeter();

        }
    }
    @JsonIgnore
    public ProfileFractionEntity getMatchedProfileFractionEntity() {
        return matchedProfileFractionEntity;
    }

    public void setMatchedProfileFractionEntity(ProfileFractionEntity matchedProfileFractionEntity) {
        this.matchedProfileFractionEntity = matchedProfileFractionEntity;
    }

    public Double getConsumtion() {
        return consumtion;
    }

    public void setConsumtion(Double consumtion) {
        this.consumtion = consumtion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeterReadingEntity that = (MeterReadingEntity) o;

        if (!meterEntity.equals(that.meterEntity)) return false;
        if (month != that.month) return false;
        return readedMeter.equals(that.readedMeter);
    }

    @Override
    public int hashCode() {
        int result = meterEntity.hashCode();
        result = 31 * result + month.hashCode();
        return result;
    }
}
