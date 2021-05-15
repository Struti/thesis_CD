package com.me.thesis.holiday.service.holidayhistory;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayEvent;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidayhistory.transformer.HolidayEventDomainTransformer;
import com.me.thesis.holiday.dto.holidayhistory.HolidayEventDto;
import com.me.thesis.holiday.service.holidayhistory.calculation.AvailableHolidaysCalculator;
import com.me.thesis.holiday.service.security.AuthenticatedUserService;

/**
 * The type User holiday management service.
 */
@Service
public class UserHolidayManagementService {

    @Autowired
    private AvailableHolidaysCalculator calculator;

    @Autowired
    private HolidayHistoryService holidayHistoryService;

    @Autowired
    private HolidayHistorySaveService historySaveService;

    @Autowired
    private HolidayEventDomainTransformer transformer;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    /**
     * Gets available holidays.
     *
     * @return the available holidays
     */
    public BigDecimal getAvailableHolidays() {
        Long userId = authenticatedUserService.getAuthenticatedPersonDetails();
        return calculator.calculateAvailableHolidays(userId);
    }

    /**
     * Create new holiday.
     *
     * @param holidayEvent the holiday event
     */
    public void createNewHoliday(HolidayEventDto holidayEvent) {
        Long userId = authenticatedUserService.getAuthenticatedPersonDetails();
        HolidayHistory history = holidayHistoryService.getHistoryByPersonId(userId);
        HolidayEvent event = transformer.transform(holidayEvent);
        historySaveService.saveHistory(history, event, event.getStartDate()
            .getYear());
    }

}
