package com.me.thesis.holiday.dal.holidaytypes.death.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.death.domain.DeathHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.death.repository.DeathHolidaysRepository;

/**
 * The type Death holidays dao.
 */
@Component
public class DeathHolidaysDao {

    @Autowired
    private DeathHolidaysRepository repository;

    /**
     * Gets death holidays.
     *
     * @return the death holidays
     */
    public List<DeathHolidaysEntity> getDeathHolidays() {
        return repository.findAll();
    }

    /**
     * Save death holiday.
     *
     * @param holiday the holiday
     */
    public void saveDeathHoliday(DeathHolidaysEntity holiday) {
        repository.save(holiday);
    }

    /**
     * Delete death holiday.
     *
     * @param holidayId the holiday id
     */
    public void deleteDeathHoliday(Long holidayId) {
        repository.deleteById(holidayId);
    }

}
