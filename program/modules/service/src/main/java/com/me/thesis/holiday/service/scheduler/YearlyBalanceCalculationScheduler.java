package com.me.thesis.holiday.service.scheduler;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.holidayhistory.calculation.HolidayBalanceCalculator;

/**
 * The type Yearly balance calculation scheduler.
 */
@Service
public class YearlyBalanceCalculationScheduler {

    @Autowired
    private HolidayBalanceCalculator holidayBalanceCalculator;

    /**
     * Calculate balance.
     *
     * @param persons          the persons
     * @param nationalHolidays the national holidays
     */
    public void calculateBalance(Set<PersonDetails> persons, List<LocalDate> nationalHolidays) {
        persons.forEach(person -> holidayBalanceCalculator.calculateFullUserHolidayBalance(person, nationalHolidays));
    }
}
