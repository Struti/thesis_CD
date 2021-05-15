package com.me.thesis.holiday.dal.nationalholiday.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.nationalholiday.domain.NationalHolidayEntity;

/**
 * The interface National holiday repository.
 */
@Repository
public interface NationalHolidayRepository extends JpaRepository<NationalHolidayEntity, Long> {

    /**
     * Delete all by date before.
     *
     * @param date the date
     */
    void deleteAllByDateBefore(LocalDate date);

}
