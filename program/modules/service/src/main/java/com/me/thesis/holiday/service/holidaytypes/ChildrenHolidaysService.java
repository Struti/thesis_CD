package com.me.thesis.holiday.service.holidaytypes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.holidaytypes.children.dao.ChildrenHolidaysDao;
import com.me.thesis.holiday.dal.holidaytypes.children.transformer.entitytransformer.ChildrenHolidaysEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;

/**
 * The type Children holidays service.
 */
@Service
public class ChildrenHolidaysService {

    @Autowired
    private ChildrenHolidaysDao dao;

    @Autowired
    private ChildrenHolidaysEntityTransformer transformer;

    /**
     * Gets all children holiday details.
     *
     * @return the all children holiday details
     */
    public List<ChildrenHolidays> getAllChildrenHolidayDetails() {
        return transformer.transform(dao.getAllChildren());
    }

    /**
     * Save children holiday.
     *
     * @param domain the domain
     */
    public void saveChildrenHoliday(ChildrenHolidays domain) {
        dao.saveChildren(transformer.transform(domain));
    }

    /**
     * Delete age based holiday details.
     *
     * @param holidayId the holiday id
     */
    public void deleteAgeBasedHolidayDetails(Long holidayId) {
        dao.deleteChildren(holidayId);
    }

}
