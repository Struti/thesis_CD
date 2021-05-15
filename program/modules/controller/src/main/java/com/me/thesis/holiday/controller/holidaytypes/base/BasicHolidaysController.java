package com.me.thesis.holiday.controller.holidaytypes.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.domain.holidaytypes.basic.transformer.BasicHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.BasicHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.BasicHolidaysService;

/**
 * The type Basic holidays controller.
 */
@RestController
public class BasicHolidaysController {

    private static final String ADMIN_HOLIDAYS_BASIC_GET_ALL_BASE_HOLIDAY_TYPE = "/admin/holidays/basic/getAllBasicHolidayType";
    private static final String ADMIN_HOLIDAYS_BASIC_SAVE_BASE_HOLIDAY_TYPE = "/admin/holidays/basic/saveBasicHolidayType";
    private static final String ADMIN_HOLIDAYS_BASIC_DELETE_BASE_HOLIDAY_TYPE = "/admin/holidays/basic/deleteBasicHolidayType/{id}";

    @Autowired
    private BasicHolidaysService service;

    @Autowired
    private BasicHolidaysTransformer transformer;

    /**
     * Gets all basic holiday details.
     *
     * @return the all basic holiday details
     */
    @GetMapping(ADMIN_HOLIDAYS_BASIC_GET_ALL_BASE_HOLIDAY_TYPE)
    public List<BasicHolidays> getAllBasicHolidayDetails() {
        return service.getAllBasicHolidayDetails();
    }

    /**
     * Save basic holiday.
     *
     * @param holiday the holiday
     */
    @PostMapping(ADMIN_HOLIDAYS_BASIC_SAVE_BASE_HOLIDAY_TYPE)
    public void saveBasicHoliday(@RequestBody BasicHolidayDetailsDto holiday) {
        service.saveBasicdHoliday(transformer.transform(holiday));
    }

    /**
     * Delete basic holiday.
     *
     * @param holidayId the holiday id
     */
    @DeleteMapping(ADMIN_HOLIDAYS_BASIC_DELETE_BASE_HOLIDAY_TYPE)
    public void deleteBasicHoliday(@PathVariable("id") Long holidayId) {
        service.deleteBasicHolidayDetails(holidayId);
    }

}
