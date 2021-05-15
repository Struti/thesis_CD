package com.me.thesis.holiday.dal.holidaytypes.age.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.age.domain.AgeBasedHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.age.repository.AgeBasedHolidaysRepository;

/**
 * The type Age based holidays dao.
 */
@Component
public class AgeBasedHolidaysDao {

    @Autowired
    private AgeBasedHolidaysRepository repository;

    /**
     * Gets age based holidays.
     *
     * @return the age based holidays
     */
    public List<AgeBasedHolidaysEntity> getAgeBasedHolidays() {
        return repository.findAll();
    }

    /**
     * Save age based holiday.
     *
     * @param holiday the holiday
     */
    public void saveAgeBasedHoliday(AgeBasedHolidaysEntity holiday) {
        repository.save(holiday);
    }

    /**
     * Delete age based holiday.
     *
     * @param holidayId the holiday id
     */
    public void deleteAgeBasedHoliday(Long holidayId) {
        repository.deleteById(holidayId);
    }

}
