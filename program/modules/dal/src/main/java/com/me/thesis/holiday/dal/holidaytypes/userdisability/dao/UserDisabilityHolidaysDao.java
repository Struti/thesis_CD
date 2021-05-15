package com.me.thesis.holiday.dal.holidaytypes.userdisability.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.userdisability.domain.UserDisabilityHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.userdisability.repository.UserDisabilityHolidayRepository;

/**
 * The type User disability holidays dao.
 */
@Component
public class UserDisabilityHolidaysDao {

    @Autowired
    private UserDisabilityHolidayRepository repository;

    /**
     * Gets all user disability holidays.
     *
     * @return the all user disability holidays
     */
    public List<UserDisabilityHolidaysEntity> getAllUserDisabilityHolidays() {
        return repository.findAll();
    }

    /**
     * Save user disability holiday.
     *
     * @param holiday the holiday
     */
    public void saveUserDisabilityHoliday(UserDisabilityHolidaysEntity holiday) {
        repository.save(holiday);
    }

    /**
     * Delete user disability holiday.
     *
     * @param holidayId the holiday id
     */
    public void deleteUserDisabilityHoliday(Long holidayId) {
        repository.deleteById(holidayId);
    }

}
