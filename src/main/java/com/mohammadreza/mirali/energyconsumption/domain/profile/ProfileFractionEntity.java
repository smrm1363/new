package com.mohammadreza.mirali.energyconsumption.domain.profile;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;


@Entity
@IdClass(ProfileFractionId.class)
public class ProfileFractionEntity {


    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private ProfileEntity profileEntity;

    @Id
    @NotNull
    @Enumerated(EnumType.STRING)
    private MonthEnum month;

    @NotNull
    @DecimalMax("1.0")
    @DecimalMin("0.0")
    private Double fraction;


    public ProfileFractionEntity(@NotNull ProfileEntity profileEntity, @NotNull MonthEnum month, @NotNull @DecimalMax("1.0") @DecimalMin("0.0") Double fraction) {
        this.profileEntity = profileEntity;
        this.month = month;
        this.fraction = fraction;
    }

    public ProfileFractionEntity() {

    }

    public ProfileEntity getProfileEntity() {
        return profileEntity;
    }

    public void setProfileEntity(ProfileEntity profileEntity) {
        this.profileEntity = profileEntity;
    }

    public MonthEnum getMonth() {
        return month;
    }

    public void setMonth(MonthEnum month) {
        this.month = month;
    }

    public Double getFraction() {
        return fraction;
    }

    public void setFraction(Double fraction) {
        this.fraction = fraction;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileFractionEntity that = (ProfileFractionEntity) o;

        if (!profileEntity.equals(that.profileEntity)) return false;
        return month == that.month;
    }

    @Override
    public int hashCode() {
        int result = profileEntity.hashCode();
        result = 31 * result + month.hashCode();
        return result;
    }
}
