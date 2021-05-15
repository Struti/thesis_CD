package com.me.thesis.holiday.dal.holidaytypes.maternity.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.maternity.domain.MaternityHolidayEntity;
import com.me.thesis.holiday.dal.holidaytypes.maternity.repository.MaternityHolidayRepository;

/**
 * The type Maternity holiday dao.
 */
@Component
public class MaternityHolidayDao {

    @Autowired
    private MaternityHolidayRepository repository;

    /**
     * Gets all maternity holidays.
     *
     * @return the all maternity holidays
     */
    public List<MaternityHolidayEntity> getAllMaternityHolidays() {
        return repository.findAll();
    }

    /**
     * Save maternity holiday.
     *
     * @param holiday the holiday
     */
    public void saveMaternityHoliday(MaternityHolidayEntity holiday) {
        repository.save(holiday);
    }

    /**
     * Delete maternity holiday.
     *
     * @param holidayId the holiday id
     */
    public void deleteMaternityHoliday(Long holidayId) {
        repository.deleteById(holidayId);
    }

}
