package com.me.thesis.holiday.service.holidayhistory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.holidayhistory.calculation.HolidayBalanceCalculator;
import com.me.thesis.holiday.service.nationalholiday.NationalHolidayService;
import com.me.thesis.holiday.service.person.PersonService;

/**
 * The type Holiday history manual calculation service.
 */
@Service
public class HolidayHistoryManualCalculationService {

    @Autowired
    private PersonService personService;

    @Autowired
    private NationalHolidayService nationalHolidayService;

    @Autowired
    private HolidayBalanceCalculator holidayBalanceCalculator;

    /**
     * Calculate person details.
     *
     * @param personId the person id
     *
     * @return the person details
     */
    public PersonDetails calculate(Long personId) {
        holidayBalanceCalculator.calculateFullUserHolidayBalance(getPerson(personId), getHolidays());
        return getPerson(personId);
    }

    private PersonDetails getPerson(Long personId) {
        return personService.getPersonById(personId);
    }

    private List<LocalDate> getHolidays() {
        return nationalHolidayService.getNationalHolidays()
            .stream()
            .map(NationalHoliday::getDate)
            .collect(Collectors.toList());
    }

}
