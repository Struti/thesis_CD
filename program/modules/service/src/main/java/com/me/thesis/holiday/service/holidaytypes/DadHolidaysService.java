package com.me.thesis.holiday.service.holidaytypes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.holidaytypes.dad.dao.DadHolidaysDao;
import com.me.thesis.holiday.dal.holidaytypes.dad.transformers.entitytransformer.DadHolidaysEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.dad.domain.DadHolidays;

/**
 * The type Dad holidays service.
 */
@Service
public class DadHolidaysService {

    @Autowired
    private DadHolidaysDao dao;

    @Autowired
    private DadHolidaysEntityTransformer transformer;

    /**
     * Gets all dad holidays.
     *
     * @return the all dad holidays
     */
    public List<DadHolidays> getAllDadHolidays() {
        return transformer.transform(dao.getAllDadHolidays());
    }

    /**
     * Save dad holiday.
     *
     * @param domain the domain
     */
    public void saveDadHoliday(DadHolidays domain) {
        dao.saveDadHoliday(transformer.transform(domain));
    }

    /**
     * Delete dad holiday.
     *
     * @param holidayId the holiday id
     */
    public void deleteDadHoliday(Long holidayId) {
        dao.deleteDadHoliday(holidayId);
    }

}
