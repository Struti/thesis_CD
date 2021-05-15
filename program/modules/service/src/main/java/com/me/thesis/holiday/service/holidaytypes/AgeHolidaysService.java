package com.me.thesis.holiday.service.holidaytypes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.holidaytypes.age.dao.AgeBasedHolidaysDao;
import com.me.thesis.holiday.dal.holidaytypes.age.transformer.entitytransformer.AgeBasedHolidaysEntityTransfer;
import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;

/**
 * The type Age holidays service.
 */
@Service
public class AgeHolidaysService {

    @Autowired
    private AgeBasedHolidaysDao dao;

    @Autowired
    private AgeBasedHolidaysEntityTransfer transformer;

    /**
     * Gets all age based holiday details.
     *
     * @return the all age based holiday details
     */
    public List<AgeBasedHolidays> getAllAgeBasedHolidayDetails() {
        return transformer.transform(dao.getAgeBasedHolidays());
    }

    /**
     * Save age based holiday.
     *
     * @param domain the domain
     */
    public void saveAgeBasedHoliday(AgeBasedHolidays domain) {
        dao.saveAgeBasedHoliday(transformer.transform(domain));
    }

    /**
     * Delete age based holiday details.
     *
     * @param holidayId the holiday id
     */
    public void deleteAgeBasedHolidayDetails(Long holidayId) {
        dao.deleteAgeBasedHoliday(holidayId);
    }

}
