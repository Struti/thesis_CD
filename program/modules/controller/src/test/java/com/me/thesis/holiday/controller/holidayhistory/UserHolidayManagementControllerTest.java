package com.me.thesis.holiday.controller.holidayhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dto.holidayhistory.HolidayEventDto;
import com.me.thesis.holiday.service.holidayhistory.UserHolidayManagementService;

class UserHolidayManagementControllerTest {

    private static final BigDecimal EXPECTED = BigDecimal.TEN;
    private static final String DESCRIPTION = "desc";
    @InjectMocks
    private UserHolidayManagementController underTest;

    @Mock
    private UserHolidayManagementService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAvailableHolidaysShould() {
        //GIVEN
        when(service.getAvailableHolidays()).thenReturn(EXPECTED);
        //WHEN
        verifyNoInteractions(service);
        BigDecimal actual = underTest.getAvailableHolidays();
        //THEN
        verify(service).getAvailableHolidays();
        verifyNoMoreInteractions(service);
        assertEquals(EXPECTED, actual);
    }

    @Test
    void testCreateNewHolidayShouldAddToHistory() {
        //GIVEN
        HolidayEventDto eventDto = createEventDto();
        //WHEN
        verifyNoInteractions(service);
        underTest.createNewHoliday(eventDto);
        //THEN
        verify(service).createNewHoliday(eventDto);
        verifyNoMoreInteractions(service);
    }

    private HolidayEventDto createEventDto() {
        return HolidayEventDto.builder()
            .description(DESCRIPTION)
            .startDate(LocalDate.MIN)
            .endDate(LocalDate.MAX)
            .dadHoliday(false)
            .build();
    }

}
