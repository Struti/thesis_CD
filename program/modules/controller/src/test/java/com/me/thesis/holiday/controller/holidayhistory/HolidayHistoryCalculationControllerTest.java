package com.me.thesis.holiday.controller.holidayhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.holidayhistory.HolidayHistoryManualCalculationService;

class HolidayHistoryCalculationControllerTest {

    private static final long PERSON_ID = 1111L;

    @InjectMocks
    private HolidayHistoryCalculationController underTest;

    @Mock
    private HolidayHistoryManualCalculationService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculatePersonalHistoryShould() {
        //GIVEN
        PersonDetails expected = getPersonDetails();
        when(service.calculate(PERSON_ID)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(service);
        PersonDetails actual = underTest.calculatePersonalHistory(PERSON_ID);
        //THEN
        verify(service).calculate(PERSON_ID);
        verifyNoMoreInteractions(service);
        assertEquals(expected, actual);
    }

    private PersonDetails getPersonDetails() {
        return PersonDetails.builder()
            .id(PERSON_ID)
            .build();
    }

}
