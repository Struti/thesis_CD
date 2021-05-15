package com.me.thesis.holiday.dal.holidaytypes.death.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.holidaytypes.death.domain.DeathHolidaysEntity;

/**
 * The interface Death holidays repository.
 */
@Repository
public interface DeathHolidaysRepository extends JpaRepository<DeathHolidaysEntity, Long> {

}
