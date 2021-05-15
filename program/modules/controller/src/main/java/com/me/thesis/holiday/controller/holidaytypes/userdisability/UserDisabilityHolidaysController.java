package com.me.thesis.holiday.controller.holidaytypes.userdisability;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.transformer.UserDisabilityHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.UserDisabilityHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.UserDisabilityHolidaysService;

/**
 * The type User disability holidays controller.
 */
@RestController
public class UserDisabilityHolidaysController {

    private static final String ADMIN_HOLIDAYS_USER_DISABILITY_GET_ALL_USER_DISABILITY_HOLIDAY_TYPE = "/admin/holidays/userDisability/getAllUserDisabilityHolidayType";
    private static final String ADMIN_HOLIDAYS_USER_DISABILITY_SAVE_USER_DISABILITY_HOLIDAY_TYPE = "/admin/holidays/userDisability/saveUserDisabilityHolidayType";
    private static final String ADMIN_HOLIDAYS_USER_DISABILITY_DELETE_USER_DISABILITY_HOLIDAY_TYPE = "/admin/holidays/userDisability/deleteUserDisabilityHolidayType/{id}";

    @Autowired
    private UserDisabilityHolidaysService service;

    @Autowired
    private UserDisabilityHolidaysTransformer transformer;

    /**
     * Gets all user disability holidays.
     *
     * @return the all user disability holidays
     */
    @GetMapping(ADMIN_HOLIDAYS_USER_DISABILITY_GET_ALL_USER_DISABILITY_HOLIDAY_TYPE)
    public List<UserDisabilityHolidays> getAllUserDisabilityHolidays() {
        return service.getAllUserDisabilityHolidays();
    }

    /**
     * Save user disability holiday.
     *
     * @param userDisabilityHolidayDetailsDto the user disability holiday details dto
     */
    @PostMapping(ADMIN_HOLIDAYS_USER_DISABILITY_SAVE_USER_DISABILITY_HOLIDAY_TYPE)
    public void saveUserDisabilityHoliday(@RequestBody UserDisabilityHolidayDetailsDto userDisabilityHolidayDetailsDto) {
        service.saveUserDisabilityHoliday(transformer.transform(userDisabilityHolidayDetailsDto));
    }

    /**
     * Delete user disability holiday.
     *
     * @param holidayId the holiday id
     */
    @DeleteMapping(ADMIN_HOLIDAYS_USER_DISABILITY_DELETE_USER_DISABILITY_HOLIDAY_TYPE)
    public void deleteUserDisabilityHoliday(@PathVariable("id") Long holidayId) {
        service.deleteUserDisabilityHoliday(holidayId);
    }

}
