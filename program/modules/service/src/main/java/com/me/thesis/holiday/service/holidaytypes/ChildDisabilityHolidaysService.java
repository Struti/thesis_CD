package com.me.thesis.holiday.service.holidaytypes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.holidaytypes.childdisability.dao.ChildDisabilityHolidayDao;
import com.me.thesis.holiday.dal.holidaytypes.childdisability.transformer.entitytransformer.ChildDisabilityHolidaysEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;

/**
 * The type Child disability holidays service.
 */
@Service
public class ChildDisabilityHolidaysService {

    @Autowired
    private ChildDisabilityHolidayDao dao;

    @Autowired
    private ChildDisabilityHolidaysEntityTransformer transformer;

    /**
     * Gets all child disability holiday details.
     *
     * @return the all child disability holiday details
     */
    public List<ChildDisabilityHolidays> getAllChildDisabilityHolidayDetails() {
        return transformer.transform(dao.getChildrenDisabilityHolidays());
    }

    /**
     * Save child disability holiday.
     *
     * @param domain the domain
     */
    public void saveChildDisabilityHoliday(ChildDisabilityHolidays domain) {
        dao.saveChildDisabilityHoliday(transformer.transform(domain));
    }

    /**
     * Delete child disability holiday details.
     *
     * @param holidayId the holiday id
     */
    public void deleteChildDisabilityHolidayDetails(Long holidayId) {
        dao.deleteChildDisabilityHoliday(holidayId);
    }

}
