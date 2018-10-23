package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * This is a repository for MeterReadingEntity
 */
@Repository
public interface MeterReadingRepository extends JpaRepository<MeterReadingEntity,String> {
    /**
     * This is a custom finding method
     */
    List<MeterReadingEntity> findByMeterEntityIdAndMonth(String meterEntity_id, MonthEnum month);
}

