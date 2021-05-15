package com.me.thesis.holiday.dal.holidaytypes.maternity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.holidaytypes.maternity.domain.MaternityHolidayEntity;

/**
 * The interface Maternity holiday repository.
 */
@Repository
public interface MaternityHolidayRepository extends JpaRepository<MaternityHolidayEntity, Long> {

}
