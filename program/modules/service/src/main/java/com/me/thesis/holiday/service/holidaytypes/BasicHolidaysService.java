package com.me.thesis.holiday.service.holidaytypes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.holidaytypes.basic.dao.BasicHolidaysDao;
import com.me.thesis.holiday.dal.holidaytypes.basic.transformers.entitytransformer.BasicHolidaysEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;

/**
 * The type Basic holidays service.
 */
@Service
public class BasicHolidaysService {

    @Autowired
    private BasicHolidaysDao dao;

    @Autowired
    private BasicHolidaysEntityTransformer transformer;

    /**
     * Gets all basic holiday details.
     *
     * @return the all basic holiday details
     */
    public List<BasicHolidays> getAllBasicHolidayDetails() {
        return transformer.transform(dao.getBasicHolidays());
    }

    /**
     * Save basicd holiday.
     *
     * @param domain the domain
     */
    public void saveBasicdHoliday(BasicHolidays domain) {
        dao.saveBasicHoliday(transformer.transform(domain));
    }

    /**
     * Delete basic holiday details.
     *
     * @param holidayId the holiday id
     */
    public void deleteBasicHolidayDetails(Long holidayId) {
        dao.deleteBasicHoliday(holidayId);
    }

}
