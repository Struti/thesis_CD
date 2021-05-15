package com.me.thesis.holiday.service.scheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.lib.StartOfYearProvider;
import com.me.thesis.holiday.service.nationalholiday.NationalHolidayService;
import com.me.thesis.holiday.service.person.PersonService;

/**
 * The type Main scheduler test.
 */
class MainSchedulerTest {

    private static final int YEAR = 2021;
    private static final int ONE = 1;
    private static final LocalDate NEW_YEAR = LocalDate.of(YEAR, ONE, ONE);
    private static final LocalDate REVOLUTION_DAY = LocalDate.of(YEAR, 3, 15);
    private static final long ID = 11111L;

    @InjectMocks
    private MainScheduler underTest;

    @Mock
    private PersonService personService;

    @Mock
    private YearlyPlanSpecificationScheduler planSpecificationScheduler;

    @Mock
    private YearlyBalanceCalculationScheduler balanceCalculationScheduler;

    @Mock
    private NationalHolidayService nationalHolidayService;

    @Mock
    private StartOfYearProvider startOfYearProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testScheduleShouldCall() {
        //GIVEN
        List<NationalHoliday> nationalHolidays = getNationalHolidays();
        Set<PersonDetails> personDetails = Set.of(createPersonDetails());
        List<LocalDate> dates = List.of(NEW_YEAR, REVOLUTION_DAY);
        when(startOfYearProvider.provide()).thenReturn(NEW_YEAR);
        when(nationalHolidayService.getNationalHolidays()).thenReturn(nationalHolidays);
        when(personService.getAllPersonDetails()).thenReturn(personDetails);
        //WHEN
        verifyNoInteractions(personService, planSpecificationScheduler, balanceCalculationScheduler, nationalHolidayService, startOfYearProvider);
        underTest.schedule();
        //THEN
        verify(startOfYearProvider).provide();
        verify(nationalHolidayService).getNationalHolidays();
        verify(nationalHolidayService).addFixForNextTenYear();
        verify(nationalHolidayService).deleteEarlierNationalHolidays(NEW_YEAR);
        verify(personService).getAllPersonDetails();
        verify(planSpecificationScheduler).updatePlans(personDetails);
        verify(balanceCalculationScheduler).calculateBalance(personDetails, dates);
    }

    private List<NationalHoliday> getNationalHolidays() {
        return List.of(createNationalHoliday(NEW_YEAR), createNationalHoliday(REVOLUTION_DAY));
    }

    private PersonDetails createPersonDetails() {
        return PersonDetails.builder()
            .build();
    }

    private NationalHoliday createNationalHoliday(LocalDate date) {
        return NationalHoliday.builder()
            .id(ID)
            .date(date)
            .fix(true)
            .build();
    }

}
