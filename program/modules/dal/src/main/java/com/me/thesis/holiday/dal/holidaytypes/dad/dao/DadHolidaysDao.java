package com.me.thesis.holiday.dal.holidaytypes.dad.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.dad.domain.DadHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.dad.repository.DadHolidaysRepository;

/**
 * The type Dad holidays dao.
 */
@Component
public class DadHolidaysDao {

    @Autowired
    private DadHolidaysRepository repository;

    /**
     * Gets all dad holidays.
     *
     * @return the all dad holidays
     */
    public List<DadHolidaysEntity> getAllDadHolidays() {
        return repository.findAll();
    }

    /**
     * Save dad holiday.
     *
     * @param holiday the holiday
     */
    public void saveDadHoliday(DadHolidaysEntity holiday) {
        repository.save(holiday);
    }

    /**
     * Delete dad holiday.
     *
     * @param holidayId the holiday id
     */
    public void deleteDadHoliday(Long holidayId) {
        repository.deleteById(holidayId);
    }

}
