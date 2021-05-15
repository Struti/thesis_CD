package com.me.thesis.holiday.service.holidayhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.holidayhistory.calculation.HolidayBalanceCalculator;
import com.me.thesis.holiday.service.nationalholiday.NationalHolidayService;
import com.me.thesis.holiday.service.person.PersonService;

/**
 * The type Holiday history manual calculation service test.
 */
class HolidayHistoryManualCalculationServiceTest {

    private static final long PERSON_ID = 1111L;

    @InjectMocks
    private HolidayHistoryManualCalculationService underTest;

    @Mock
    private PersonService personService;

    @Mock
    private NationalHolidayService nationalHolidayService;

    @Mock
    private HolidayBalanceCalculator holidayBalanceCalculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateShouldCalculate() {
        //GIVEN
        PersonDetails expected = createPerson();
        List<NationalHoliday> nationalHolidays = createNationalHolidays();
        when(personService.getPersonById(PERSON_ID)).thenReturn(expected);
        when(nationalHolidayService.getNationalHolidays()).thenReturn(nationalHolidays);
        //WHEN
        verifyNoInteractions(personService, nationalHolidayService);
        PersonDetails actual = underTest.calculate(PERSON_ID);
        //THEN
        verify(personService, times(2)).getPersonById(PERSON_ID);
        verify(nationalHolidayService).getNationalHolidays();
        verifyNoMoreInteractions(personService, nationalHolidayService);
        assertEquals(expected, actual);
    }

    private PersonDetails createPerson() {
        return PersonDetails.builder()
            .id(PERSON_ID)
            .build();
    }

    private List<NationalHoliday> createNationalHolidays() {
        return List.of(NationalHoliday.builder()
            .build());
    }

}
