package com.me.thesis.holiday.service.scheduler;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.lib.StartOfYearProvider;
import com.me.thesis.holiday.service.nationalholiday.NationalHolidayService;
import com.me.thesis.holiday.service.person.PersonService;

/**
 * The type Main scheduler.
 */
@Service
public class MainScheduler {

    private static final int YEARS_TO_SUBTRACT = 1;

    @Autowired
    private PersonService personService;

    @Autowired
    private YearlyPlanSpecificationScheduler planSpecificationScheduler;

    @Autowired
    private YearlyBalanceCalculationScheduler balanceCalculationScheduler;

    @Autowired
    private NationalHolidayService nationalHolidayService;

    @Autowired
    private StartOfYearProvider startOfYearProvider;

    /**
     * Schedule.
     */
    @Scheduled(cron = "0 0 0 1 1 ?")
    public void schedule() {
        LocalDate year = startOfYearProvider.provide();
        nationalHolidayService.addFixForNextTenYear();
        nationalHolidayService.deleteEarlierNationalHolidays(year);
        Set<PersonDetails> allPerson = getAllPerson();
        planSpecificationScheduler.updatePlans(allPerson);
        balanceCalculationScheduler.calculateBalance(allPerson, getNationalHolidays(year));
    }

    private List<LocalDate> getNationalHolidays(LocalDate year) {
        return nationalHolidayService.getNationalHolidays()
            .stream()
            .filter(nationalHoliday -> isAfter(year, nationalHoliday))
            .map(NationalHoliday::getDate)
            .collect(Collectors.toList());
    }

    private boolean isAfter(LocalDate year, NationalHoliday nationalHoliday) {
        return nationalHoliday.getDate()
            .isAfter(year.minusYears(YEARS_TO_SUBTRACT));
    }

    private Set<PersonDetails> getAllPerson() {
        return personService.getAllPersonDetails();
    }

}
