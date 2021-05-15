package com.me.thesis.holiday.controller.holidayhistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.holidayhistory.HolidayHistoryManualCalculationService;

/**
 * The type Holiday history calculation.
 */
@RestController
public class HolidayHistoryCalculationController {

    private static final String ADMIN_HOLIDAY_HISTORY_CALCULATE = "/admin/holidayHistory/calculate";

    @Autowired
    private HolidayHistoryManualCalculationService service;

    /**
     * Calculate personal history person details.
     *
     * @param personId the person id
     *
     * @return the person details
     */
    @GetMapping(ADMIN_HOLIDAY_HISTORY_CALCULATE)
    public PersonDetails calculatePersonalHistory(Long personId) {
        return service.calculate(personId);
    }

}
