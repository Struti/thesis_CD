package com.me.thesis.holiday.controller.holidaytypes.age;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;
import com.me.thesis.holiday.domain.holidaytypes.age.transformer.AgeHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.AgeHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.AgeHolidaysService;

/**
 * The type Age holidays controller.
 */
@RestController
public class AgeHolidaysController {

    private static final String ADMIN_HOLIDAYS_AGE_GET_ALL_AGE_HOLIDAY_TYPE = "/admin/holidays/age/getAllAgeHolidayType";
    private static final String ADMIN_HOLIDAYS_AGE_SAVE_AGE_HOLIDAY_TYPE = "/admin/holidays/age/saveAgeHolidayType";
    private static final String ADMIN_HOLIDAYS_AGE_DELETE_AGE_HOLIDAY_TYPE = "/admin/holidays/age/deleteAgeHolidayType/{id}";

    @Autowired
    private AgeHolidaysService service;

    @Autowired
    private AgeHolidaysTransformer transformer;

    /**
     * Gets all age holiday details.
     *
     * @return the all age holiday details
     */
    @GetMapping(ADMIN_HOLIDAYS_AGE_GET_ALL_AGE_HOLIDAY_TYPE)
    public List<AgeBasedHolidays> getAllAgeHolidayDetails() {
        return service.getAllAgeBasedHolidayDetails();
    }

    /**
     * Save age holiday.
     *
     * @param ageHolidayDetails the age holiday details
     */
    @PostMapping(ADMIN_HOLIDAYS_AGE_SAVE_AGE_HOLIDAY_TYPE)
    public void saveAgeHoliday(@RequestBody AgeHolidayDetailsDto ageHolidayDetails) {
        service.saveAgeBasedHoliday(transformer.transform(ageHolidayDetails));
    }

    /**
     * Delete age holiday.
     *
     * @param holidayId the holiday id
     */
    @DeleteMapping(ADMIN_HOLIDAYS_AGE_DELETE_AGE_HOLIDAY_TYPE)
    public void deleteAgeHoliday(@PathVariable("id") Long holidayId) {
        service.deleteAgeBasedHolidayDetails(holidayId);
    }

}
