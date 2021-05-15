package com.me.thesis.holiday.dal.holidaytypes.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.holidaytypes.basic.domain.BasicHolidaysEntity;

/**
 * The interface Basic holidays repository.
 */
@Repository
public interface BasicHolidaysRepository extends JpaRepository<BasicHolidaysEntity, Long> {

}
