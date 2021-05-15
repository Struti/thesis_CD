package com.me.thesis.holiday.controller.holidaytypes.maternity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.holidaytypes.maternity.domain.MaternityHoliday;
import com.me.thesis.holiday.domain.holidaytypes.maternity.transformer.MaternityHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.MaternityHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.MaternityHolidaysService;

/**
 * The type Maternity holidays controller.
 */
@RestController
public class MaternityHolidaysController {

    private static final String ADMIN_HOLIDAYS_MATERNITY_GET_ALL_MATERNITY_HOLIDAY_TYPE = "/admin/holidays/maternity/getAllMaternityHolidayType";
    private static final String ADMIN_HOLIDAYS_MATERNITY_SAVE_MATERNITY_HOLIDAY_TYPE = "/admin/holidays/maternity/saveMaternityHolidayType";
    private static final String ADMIN_HOLIDAYS_MATERNITY_DELETE_MATERNITY_HOLIDAY_TYPE = "/admin/holidays/maternity/deleteMaternityHolidayType/{id}";

    @Autowired
    private MaternityHolidaysService service;

    @Autowired
    private MaternityHolidaysTransformer transformer;

    /**
     * Gets all maternity holidays.
     *
     * @return the all maternity holidays
     */
    @GetMapping(ADMIN_HOLIDAYS_MATERNITY_GET_ALL_MATERNITY_HOLIDAY_TYPE)
    public List<MaternityHoliday> getAllMaternityHolidays() {
        return service.getAllMaternityHolidays();
    }

    /**
     * Save maternity holiday.
     *
     * @param MaternityHolidayDetails the maternity holiday details
     */
    @PostMapping(ADMIN_HOLIDAYS_MATERNITY_SAVE_MATERNITY_HOLIDAY_TYPE)
    public void saveMaternityHoliday(@RequestBody MaternityHolidayDetailsDto MaternityHolidayDetails) {
        service.saveMaternityHoliday(transformer.transform(MaternityHolidayDetails));
    }

    /**
     * Delete maternity holiday.
     *
     * @param holidayId the holiday id
     */
    @DeleteMapping(ADMIN_HOLIDAYS_MATERNITY_DELETE_MATERNITY_HOLIDAY_TYPE)
    public void deleteMaternityHoliday(@PathVariable("id") Long holidayId) {
        service.deleteMaternityHoliday(holidayId);
    }

}
