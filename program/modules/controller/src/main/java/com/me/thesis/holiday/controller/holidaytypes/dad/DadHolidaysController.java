package com.me.thesis.holiday.controller.holidaytypes.dad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.holidaytypes.dad.domain.DadHolidays;
import com.me.thesis.holiday.domain.holidaytypes.dad.transformer.DadHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.DadHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.DadHolidaysService;

/**
 * The type Dad holidays controller.
 */
@RestController
public class DadHolidaysController {

    private static final String ADMIN_HOLIDAYS_DAD_GET_ALL_DAD_HOLIDAY_TYPE = "/admin/holidays/dad/getAllDadHolidayType";
    private static final String ADMIN_HOLIDAYS_DAD_SAVE_DAD_HOLIDAY_TYPE = "/admin/holidays/dad/saveDadHolidayType";
    private static final String ADMIN_HOLIDAYS_DAD_DELETE_DAD_HOLIDAY_TYPE = "/admin/holidays/dad/deleteDadHolidayType/{id}";

    @Autowired
    private DadHolidaysService service;

    @Autowired
    private DadHolidaysTransformer transformer;

    /**
     * Gets all dad holidays.
     *
     * @return the all dad holidays
     */
    @GetMapping(ADMIN_HOLIDAYS_DAD_GET_ALL_DAD_HOLIDAY_TYPE)
    public List<DadHolidays> getAllDadHolidays() {
        return service.getAllDadHolidays();
    }

    /**
     * Save dad holiday.
     *
     * @param dadHolidayDetails the dad holiday details
     */
    @PostMapping(ADMIN_HOLIDAYS_DAD_SAVE_DAD_HOLIDAY_TYPE)
    public void saveDadHoliday(@RequestBody DadHolidayDetailsDto dadHolidayDetails) {
        service.saveDadHoliday(transformer.transform(dadHolidayDetails));
    }

    /**
     * Delete dad holiday.
     *
     * @param holidayId the holiday id
     */
    @DeleteMapping(ADMIN_HOLIDAYS_DAD_DELETE_DAD_HOLIDAY_TYPE)
    public void deleteDadHoliday(@PathVariable("id") Long holidayId) {
        service.deleteDadHoliday(holidayId);
    }

}
