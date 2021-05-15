package com.me.thesis.holiday.dal.holidaytypes.childdisability.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.childdisability.domain.ChildDisabilityHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.childdisability.repository.ChildDisabilityHolidayRepository;

/**
 * The type Child disability holiday dao.
 */
@Component
public class ChildDisabilityHolidayDao {

    @Autowired
    private ChildDisabilityHolidayRepository repository;

    /**
     * Gets children disability holidays.
     *
     * @return the children disability holidays
     */
    public List<ChildDisabilityHolidaysEntity> getChildrenDisabilityHolidays() {
        return repository.findAll();
    }

    /**
     * Save child disability holiday.
     *
     * @param holiday the holiday
     */
    public void saveChildDisabilityHoliday(ChildDisabilityHolidaysEntity holiday) {
        repository.save(holiday);
    }

    /**
     * Delete child disability holiday.
     *
     * @param holidayId the holiday id
     */
    public void deleteChildDisabilityHoliday(Long holidayId) {
        repository.deleteById(holidayId);
    }

}
