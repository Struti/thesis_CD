package com.me.thesis.holiday.dal.holidaytypes.userdisability.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.holidaytypes.userdisability.domain.UserDisabilityHolidaysEntity;

/**
 * The interface User disability holiday repository.
 */
@Repository
public interface UserDisabilityHolidayRepository extends JpaRepository<UserDisabilityHolidaysEntity, Long> {

}
