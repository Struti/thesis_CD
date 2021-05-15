package com.me.thesis.holiday.service.holidaytypes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.holidaytypes.death.dao.DeathHolidaysDao;
import com.me.thesis.holiday.dal.holidaytypes.death.transformers.entitytransformer.DeathHolidaysEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.death.domain.DeathHolidays;

/**
 * The type Death holidays service.
 */
@Service
public class DeathHolidaysService {

    @Autowired
    private DeathHolidaysDao dao;

    @Autowired
    private DeathHolidaysEntityTransformer transformer;

    /**
     * Gets all death holiday details.
     *
     * @return the all death holiday details
     */
    public List<DeathHolidays> getAllDeathHolidayDetails() {
        return transformer.transform(dao.getDeathHolidays());
    }

    /**
     * Save death holiday.
     *
     * @param deathHolidayDetails the death holiday details
     */
    public void saveDeathHoliday(DeathHolidays deathHolidayDetails) {
        dao.saveDeathHoliday(transformer.transform(deathHolidayDetails));
    }

    /**
     * Delete death holiday details.
     *
     * @param holidayId the holiday id
     */
    public void deleteDeathHolidayDetails(Long holidayId) {
        dao.deleteDeathHoliday(holidayId);
    }

}
