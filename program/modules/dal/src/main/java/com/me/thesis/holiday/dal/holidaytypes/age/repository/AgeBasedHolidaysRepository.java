package com.me.thesis.holiday.dal.holidaytypes.age.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.holidaytypes.age.domain.AgeBasedHolidaysEntity;

/**
 * The interface Age based holidays repository.
 */
@Repository
public interface AgeBasedHolidaysRepository extends JpaRepository<AgeBasedHolidaysEntity, Long> {

}
