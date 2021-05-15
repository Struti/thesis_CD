package com.me.thesis.holiday.service.holidaytypes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.holidaytypes.maternity.dao.MaternityHolidayDao;
import com.me.thesis.holiday.dal.holidaytypes.maternity.transformers.entitytransformer.MaternityHolidayEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.maternity.domain.MaternityHoliday;

/**
 * The type Maternity holidays service.
 */
@Service
public class MaternityHolidaysService {

    @Autowired
    private MaternityHolidayDao dao;

    @Autowired
    private MaternityHolidayEntityTransformer transformer;

    /**
     * Gets all maternity holidays.
     *
     * @return the all maternity holidays
     */
    public List<MaternityHoliday> getAllMaternityHolidays() {
        return transformer.transform(dao.getAllMaternityHolidays());
    }

    /**
     * Save maternity holiday.
     *
     * @param domain the domain
     */
    public void saveMaternityHoliday(MaternityHoliday domain) {
        dao.saveMaternityHoliday(transformer.transform(domain));
    }

    /**
     * Delete maternity holiday.
     *
     * @param holidayId the holiday id
     */
    public void deleteMaternityHoliday(Long holidayId) {
        dao.deleteMaternityHoliday(holidayId);
    }

}
