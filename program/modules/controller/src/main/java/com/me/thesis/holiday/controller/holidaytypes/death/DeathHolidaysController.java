package com.me.thesis.holiday.controller.holidaytypes.death;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.holidaytypes.death.domain.DeathHolidays;
import com.me.thesis.holiday.domain.holidaytypes.death.transformer.DeathHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.DeathHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.DeathHolidaysService;

/**
 * The type Death holidays controller.
 */
@RestController
public class DeathHolidaysController {

    private static final String ADMIN_HOLIDAYS_DEATH_GET_ALL_DEATH_HOLIDAY_TYPE = "/admin/holidays/death/getAllDeathHolidayType";
    private static final String ADMIN_HOLIDAYS_DEATH_SAVE_DEATH_HOLIDAY_TYPE = "/admin/holidays/death/saveDeathHolidayType";
    private static final String ADMIN_HOLIDAYS_DEATH_DELETE_DEATH_HOLIDAY_TYPE = "/admin/holidays/death/deleteDeathHolidayType/{id}";

    @Autowired
    private DeathHolidaysService service;

    @Autowired
    private DeathHolidaysTransformer transformer;

    /**
     * Gets all death holiday details.
     *
     * @return the all death holiday details
     */
    @GetMapping(ADMIN_HOLIDAYS_DEATH_GET_ALL_DEATH_HOLIDAY_TYPE)
    public List<DeathHolidays> getAllDeathHolidayDetails() {
        return service.getAllDeathHolidayDetails();
    }

    /**
     * Delete death holiday.
     *
     * @param holidayId the holiday id
     */
    @PostMapping(ADMIN_HOLIDAYS_DEATH_DELETE_DEATH_HOLIDAY_TYPE)
    public void deleteDeathHoliday(@PathVariable("id") Long holidayId) {
        service.deleteDeathHolidayDetails(holidayId);
    }

    /**
     * Save death holiday.
     *
     * @param deathHolidayDetails the death holiday details
     */
    @DeleteMapping(ADMIN_HOLIDAYS_DEATH_SAVE_DEATH_HOLIDAY_TYPE)
    public void saveDeathHoliday(@RequestBody DeathHolidayDetailsDto deathHolidayDetails) {
        service.saveDeathHoliday(transformer.transform(deathHolidayDetails));
    }

}
