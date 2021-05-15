package com.me.thesis.holiday.controller.holidayhistory;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.dto.holidayhistory.HolidayEventDto;
import com.me.thesis.holiday.service.holidayhistory.UserHolidayManagementService;

/**
 * The type User holiday management controller.
 */
@RestController
public class UserHolidayManagementController {

    private static final String GET_AVAILABLE_HOLIDAYS = "/user/getAvailableHolidays";
    private static final String CREATE_NEW_HOLIDAY = "/user/createNewHoliday";

    @Autowired
    private UserHolidayManagementService service;

    /**
     * Gets available holidays.
     * @return the available holidays
     */
    @PostMapping(GET_AVAILABLE_HOLIDAYS)
    public BigDecimal getAvailableHolidays() {
        return service.getAvailableHolidays();
    }

    /**
     * Create new holiday.
     *
     * @param holidayEvent the holiday event
     */
    @PostMapping(CREATE_NEW_HOLIDAY)
    public void createNewHoliday(@RequestBody HolidayEventDto holidayEvent) {
        service.createNewHoliday(holidayEvent);
    }

}
