package com.me.thesis.holiday.controller.holidaytypes.childdisability;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.transformer.ChildDisabilityHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.ChildDisabilityHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.ChildDisabilityHolidaysService;

/**
 * The type Child disability holidays controller.
 */
@RestController
public class ChildDisabilityHolidaysController {

    private static final String ADMIN_HOLIDAYS_CHILD_DISABILITY_GET_ALL_CHILD_DISABILITY_HOLIDAY_TYPE = "/admin/holidays/childDisability/getAllChildDisabilityHolidayType";
    private static final String ADMIN_HOLIDAYS_CHILD_DISABILITY_SAVE_CHILD_DISABILITY_HOLIDAY_TYPE = "/admin/holidays/childDisability/saveChildDisabilityHolidayType";
    private static final String ADMIN_HOLIDAYS_CHILD_DISABILITY_DELETE_CHILD_DISABILITY_HOLIDAY_TYPE = "/admin/holidays/childDisability/deleteChildDisabilityHolidayType/{id}";

    @Autowired
    private ChildDisabilityHolidaysService service;

    @Autowired
    private ChildDisabilityHolidaysTransformer transformer;

    /**
     * Gets all child disability holiday details.
     *
     * @return the all child disability holiday details
     */
    @GetMapping(ADMIN_HOLIDAYS_CHILD_DISABILITY_GET_ALL_CHILD_DISABILITY_HOLIDAY_TYPE)
    public List<ChildDisabilityHolidays> getAllChildDisabilityHolidayDetails() {
        return service.getAllChildDisabilityHolidayDetails();
    }

    /**
     * Save child disability holiday.
     *
     * @param childDisabilityHolidayDetails the child disability holiday details
     */
    @PostMapping(ADMIN_HOLIDAYS_CHILD_DISABILITY_SAVE_CHILD_DISABILITY_HOLIDAY_TYPE)
    public void saveChildDisabilityHoliday(@RequestBody ChildDisabilityHolidayDetailsDto childDisabilityHolidayDetails) {
        service.saveChildDisabilityHoliday(transformer.transform(childDisabilityHolidayDetails));
    }

    /**
     * Delete child disability holiday.
     *
     * @param holidayId the holiday id
     */
    @DeleteMapping(ADMIN_HOLIDAYS_CHILD_DISABILITY_DELETE_CHILD_DISABILITY_HOLIDAY_TYPE)
    public void deleteChildDisabilityHoliday(@PathVariable("id") Long holidayId) {
        service.deleteChildDisabilityHolidayDetails(holidayId);
    }

}
