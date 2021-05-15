package com.me.thesis.holiday.service.scheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.holidayhistory.calculation.HolidayBalanceCalculator;

class YearlyBalanceCalculationSchedulerTest {

    private static final LocalDate DATE = LocalDate.EPOCH;

    @InjectMocks
    private YearlyBalanceCalculationScheduler underTest;

    @Mock
    private HolidayBalanceCalculator holidayBalanceCalculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateBalanceShouldCalculate() {
        //GIVEN
        PersonDetails person = createPerson();
        Set<PersonDetails> personDetails = Set.of(person);
        List<LocalDate> dates = List.of(DATE);
        //WHEN
        verifyNoInteractions(holidayBalanceCalculator);
        underTest.calculateBalance(personDetails, dates);
        //THEN
        verify(holidayBalanceCalculator).calculateFullUserHolidayBalance(person, dates);
        verifyNoMoreInteractions(holidayBalanceCalculator);
    }

    private PersonDetails createPerson() {
        return PersonDetails.builder()
            .build();
    }

}
