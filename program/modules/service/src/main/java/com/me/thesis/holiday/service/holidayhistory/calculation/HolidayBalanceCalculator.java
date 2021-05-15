package com.me.thesis.holiday.service.holidayhistory.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.domain.holidayhistory.domain.YearHistory;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;
import com.me.thesis.holiday.service.holidayhistory.HolidayHistorySaveService;
import com.me.thesis.holiday.service.lib.EndOfYearProvider;
import com.me.thesis.holiday.service.lib.StartOfYearProvider;
import com.me.thesis.holiday.service.lib.YearProvider;

/**
 * The type Holiday balance calculation.
 */
@Service
public class HolidayBalanceCalculator {

    @Autowired
    private EndOfYearProvider endOfYearProvider;

    @Autowired
    private StartOfYearProvider startOfYearProvider;

    @Autowired
    private YearProvider yearProvider;

    @Autowired
    private WorkingDaysProvider workingDaysProvider;

    @Autowired
    private HolidayHistorySaveService service;

    /**
     * Calculate full user holiday balance.
     *
     * @param personDetails the person details
     * @param holidays      the holidays
     */
    public void calculateFullUserHolidayBalance(PersonDetails personDetails, List<LocalDate> holidays) {
        Year actualYear = yearProvider.provide();
        BigDecimal fullYearBalance = getFullYearBalance(personDetails.getSpecificPlan()
            .orElse(null));
        LocalDate startDate = getStartDate(personDetails.getStartDate(), actualYear);
        LocalDate lastDate = getLastDate(personDetails.getLastDay());
        BigDecimal yearlyBalance = calculateYearBalance(fullYearBalance, workingDaysProvider.provide(startDate, lastDate, holidays), actualYear);
        YearHistory yearHistory = createYearHistory(yearlyBalance, actualYear.getValue());
        service.saveHistory(personDetails.getHolidayHistory(), yearHistory, actualYear.getValue());
    }

    private BigDecimal getFullYearBalance(SpecificPlan specificPlan) {
        BigDecimal balance = getFullBalance(specificPlan);
        List<BigDecimal> fixExtraDays = getFixExtraDays(specificPlan);
        return balance.add(reduceFixExtraDays(fixExtraDays));
    }

    private LocalDate getStartDate(LocalDate personStartDate, Year actualYear) {
        return Year.of(personStartDate.getYear())
            .isBefore(actualYear) ? startOfYearProvider.provide() : personStartDate;
    }

    private LocalDate getLastDate(Optional<LocalDate> personLastDate) {
        return personLastDate.orElseGet(() -> endOfYearProvider.provide());
    }

    private BigDecimal calculateYearBalance(BigDecimal fullYearBalance, BigDecimal workingDays, Year actualYear) {
        return fullYearBalance.divide(getYearLength(actualYear).multiply(workingDays), RoundingMode.UP);
    }

    private BigDecimal getFullBalance(SpecificPlan specificPlan) {
        return BigDecimal.valueOf(specificPlan.getBasicHolidays()
            .getDay());
    }

    private YearHistory createYearHistory(BigDecimal balance, int year) {
        return YearHistory.builder()
            .year(year)
            .fixHolidaysForTheYear(balance)
            .eventRelatedHolidays(new ArrayList<>())
            .holidayEvents(new ArrayList<>())
            .build();
    }

    private BigDecimal getYearLength(Year actualYear) {
        return BigDecimal.valueOf(actualYear.length());
    }

    private List<BigDecimal> getFixExtraDays(SpecificPlan specificPlan) {
        List<BigDecimal> fixExtraDays = new ArrayList<>();
        specificPlan.getAgeBasedHolidays()
            .ifPresent(ageBasedHolidays -> fixExtraDays.add(BigDecimal.valueOf(ageBasedHolidays.getDay())));
        specificPlan.getUserDisabilityHolidays()
            .ifPresent(userDisabilityHolidays -> fixExtraDays.add(BigDecimal.valueOf(userDisabilityHolidays.getDay())));
        specificPlan.getChildrenHolidays()
            .ifPresent(childrenHolidays -> fixExtraDays.add(BigDecimal.valueOf(childrenHolidays.getDay())));
        specificPlan.getChildDisabilityHolidays()
            .ifPresent(childDisabilityHolidays -> fixExtraDays.add(BigDecimal.valueOf(childDisabilityHolidays.getDay())));
        return fixExtraDays;
    }

    private BigDecimal reduceFixExtraDays(List<BigDecimal> fixExtraDays) {
        return fixExtraDays.stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
