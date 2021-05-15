package com.me.thesis.holiday.dal.holidaytypes.dad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.holidaytypes.dad.domain.DadHolidaysEntity;

/**
 * The interface Dad holidays repository.
 */
@Repository
public interface DadHolidaysRepository extends JpaRepository<DadHolidaysEntity, Long> {

}
