package com.me.thesis.holiday.controller.holidaytypes.children;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;
import com.me.thesis.holiday.domain.holidaytypes.children.transformer.ChildrenHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.ChildrenHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.ChildrenHolidaysService;

/**
 * The type Children holidays controller.
 */
@RestController
public class ChildrenHolidaysController {

    private static final String ADMIN_HOLIDAYS_CHILDREN_GET_ALL_CHILDREN_HOLIDAY_TYPE = "/admin/holidays/children/getAllChildrenHolidayType";
    private static final String ADMIN_HOLIDAYS_CHILDREN_SAVE_CHILDREN_HOLIDAY_TYPE = "/admin/holidays/children/saveChildrenHolidayType";
    private static final String ADMIN_HOLIDAYS_CHILDREN_DELETE_CHILDREN_HOLIDAY_TYPE = "/admin/holidays/children/deleteChildrenHolidayType/{id}";

    @Autowired
    private ChildrenHolidaysService service;

    @Autowired
    private ChildrenHolidaysTransformer transformer;

    /**
     * Gets all children holiday details.
     *
     * @return the all children holiday details
     */
    @GetMapping(ADMIN_HOLIDAYS_CHILDREN_GET_ALL_CHILDREN_HOLIDAY_TYPE)
    public List<ChildrenHolidays> getAllChildrenHolidayDetails() {
        return service.getAllChildrenHolidayDetails();
    }

    /**
     * Save children holiday.
     *
     * @param childrenHolidayDetails the children holiday details
     */
    @PostMapping(ADMIN_HOLIDAYS_CHILDREN_SAVE_CHILDREN_HOLIDAY_TYPE)
    public void saveChildrenHoliday(@RequestBody ChildrenHolidayDetailsDto childrenHolidayDetails) {
        service.saveChildrenHoliday(transformer.transform(childrenHolidayDetails));
    }

    /**
     * Delete children holiday.
     *
     * @param holidayId the holiday id
     */
    @DeleteMapping(ADMIN_HOLIDAYS_CHILDREN_DELETE_CHILDREN_HOLIDAY_TYPE)
    public void deleteChildrenHoliday(@PathVariable("id") Long holidayId) {
        service.deleteAgeBasedHolidayDetails(holidayId);
    }

}
