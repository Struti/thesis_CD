package com.me.thesis.holiday.dal.holidaytypes.basic.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.basic.domain.BasicHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.basic.repository.BasicHolidaysRepository;

/**
 * The type Basic holidays dao.
 */
@Component
public class BasicHolidaysDao {

    @Autowired
    private BasicHolidaysRepository repository;

    /**
     * Gets basic holidays.
     *
     * @return the basic holidays
     */
    public List<BasicHolidaysEntity> getBasicHolidays() {
        return repository.findAll();
    }

    /**
     * Save basic holiday.
     *
     * @param holiday the holiday
     */
    public void saveBasicHoliday(BasicHolidaysEntity holiday) {
        repository.save(holiday);
    }

    /**
     * Delete basic holiday.
     *
     * @param holidayId the holiday id
     */
    public void deleteBasicHoliday(Long holidayId) {
        repository.deleteById(holidayId);
    }

}
