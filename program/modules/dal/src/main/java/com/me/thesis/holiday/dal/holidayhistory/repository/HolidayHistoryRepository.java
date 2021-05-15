package com.me.thesis.holiday.dal.holidayhistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.holidayhistory.domain.HolidayHistoryEntity;
import com.me.thesis.holiday.dal.person.enums.PersonEntityGender;

/**
 * The interface Holiday history repository.
 */
@Repository
public interface HolidayHistoryRepository extends JpaRepository<HolidayHistoryEntity, Long> {

    /**
     * Find all by person entity gender list.
     *
     * @param gender the gender
     *
     * @return the list
     */
    List<HolidayHistoryEntity> findAllByPersonEntity_Gender(PersonEntityGender gender);

    /**
     * Find by person entity person id holiday history entity.
     *
     * @param personId the person id
     *
     * @return the holiday history entity
     */
    HolidayHistoryEntity findByPersonEntity_PersonId(Long personId);

}
