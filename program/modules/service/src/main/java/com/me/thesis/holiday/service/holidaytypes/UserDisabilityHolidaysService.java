package com.me.thesis.holiday.service.holidaytypes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.holidaytypes.userdisability.dao.UserDisabilityHolidaysDao;
import com.me.thesis.holiday.dal.holidaytypes.userdisability.transformer.entitytransformer.UserDisabilityEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;

/**
 * The type User disability holidays service.
 */
@Service
public class UserDisabilityHolidaysService {

    @Autowired
    private UserDisabilityHolidaysDao dao;

    @Autowired
    private UserDisabilityEntityTransformer transformer;

    /**
     * Gets all user disability holidays.
     *
     * @return the all user disability holidays
     */
    public List<UserDisabilityHolidays> getAllUserDisabilityHolidays() {
        return transformer.transform(dao.getAllUserDisabilityHolidays());
    }

    /**
     * Save user disability holiday.
     *
     * @param domain the domain
     */
    public void saveUserDisabilityHoliday(UserDisabilityHolidays domain) {
        dao.saveUserDisabilityHoliday(transformer.transform(domain));
    }

    /**
     * Delete user disability holiday.
     *
     * @param holidayId the holiday id
     */
    public void deleteUserDisabilityHoliday(Long holidayId) {
        dao.deleteUserDisabilityHoliday(holidayId);
    }

}
