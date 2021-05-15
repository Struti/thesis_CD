package com.me.thesis.holiday.dal.holidaytypes.childdisability.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.holidaytypes.childdisability.domain.ChildDisabilityHolidaysEntity;

/**
 * The interface Child disability holiday repository.
 */
@Repository
public interface ChildDisabilityHolidayRepository extends JpaRepository<ChildDisabilityHolidaysEntity, Long> {

}
