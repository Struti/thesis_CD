package com.me.thesis.holiday.service.holidayhistory.calculation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.holidayhistory.domain.EventRelatedHoliday;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidayhistory.domain.YearHistory;
import com.me.thesis.holiday.service.holidayhistory.HolidayHistoryService;
import com.me.thesis.holiday.service.lib.CurrentDateProvider;

/**
 * The type Available holidays calculator.
 */
@Component
public class AvailableHolidaysCalculator {

    @Autowired
    private HolidayHistoryService holidayHistoryService;

    @Autowired
    private CurrentDateProvider currentDateProvider;

    /**
     * Calculate available holidays big decimal.
     *
     * @param personId the person id
     *
     * @return the big decimal
     */
    public BigDecimal calculateAvailableHolidays(long personId) {
        HolidayHistory history = getHistory(personId);
        LocalDate currentDate = getCurrentDate();
        YearHistory yearHistory = getYearHistory(history, currentDate);
        return calculate(yearHistory);
    }

    private HolidayHistory getHistory(long personId) {
        return holidayHistoryService.getHistoryByPersonId(personId);
    }

    private LocalDate getCurrentDate() {
        return currentDateProvider.provide();
    }

    private YearHistory getYearHistory(HolidayHistory history, LocalDate currentDate) {
        return history.getYearHistories()
            .stream()
            .filter(yearHistory -> yearHistory.getYear() == currentDate.getYear())
            .findFirst()
            .orElse(null);
    }

    private BigDecimal calculate(YearHistory yearHistory) {
        return yearHistory.getFixHolidaysForTheYear()
            .add(getEventRelatedHolidays(yearHistory))
            .subtract(getHolidayEvents(yearHistory));
    }

    private BigDecimal getEventRelatedHolidays(YearHistory yearHistory) {
        return yearHistory.getEventRelatedHolidays()
            .stream()
            .map(EventRelatedHoliday::getDays)
            .map(BigDecimal::new)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getHolidayEvents(YearHistory yearHistory) {
        return yearHistory.getHolidayEvents()
            .stream()
            .map(holidayEvent -> ChronoUnit.DAYS.between(holidayEvent.getStartDate(), holidayEvent.getEndDate()))
            .map(BigDecimal::new)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
