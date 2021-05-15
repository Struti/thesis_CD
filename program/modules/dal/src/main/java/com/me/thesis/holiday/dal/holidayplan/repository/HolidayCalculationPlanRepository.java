package com.me.thesis.holiday.dal.holidayplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.holidayplan.domain.HolidayCalculationPlanEntity;

/**
 * The interface Holiday calculation plan repository.
 */
@Repository
public interface HolidayCalculationPlanRepository extends JpaRepository<HolidayCalculationPlanEntity, Long> {
}
