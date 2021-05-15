package com.me.thesis.holiday.service.holidayhistory;

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

import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayEvent;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidayhistory.transformer.HolidayEventDomainTransformer;
import com.me.thesis.holiday.dto.holidayhistory.HolidayEventDto;
import com.me.thesis.holiday.service.holidayhistory.calculation.AvailableHolidaysCalculator;
import com.me.thesis.holiday.service.security.AuthenticatedUserService;

class UserHolidayManagementServiceTest {

    private static final BigDecimal EXPECTED = BigDecimal.TEN;
    private static final long PERSON_ID = 1111L;
    private static final String DESCRIPTION = "desc";
    private static final LocalDate START_DATE = LocalDate.MIN;
    private static final LocalDate END_DATE = LocalDate.MAX;

    @InjectMocks
    private UserHolidayManagementService underTest;

    @Mock
    private AvailableHolidaysCalculator calculator;

    @Mock
    private HolidayHistoryService holidayHistoryService;

    @Mock
    private HolidayHistorySaveService historySaveService;

    @Mock
    private HolidayEventDomainTransformer transformer;

    @Mock
    private AuthenticatedUserService authenticatedUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAvailableHolidaysShould() {
        //GIVEN
        when(calculator.calculateAvailableHolidays(PERSON_ID)).thenReturn(EXPECTED);
        when(authenticatedUserService.getAuthenticatedPersonDetails()).thenReturn(PERSON_ID);
        //WHEN
        verifyNoInteractions(calculator, holidayHistoryService, historySaveService, transformer, authenticatedUserService);
        BigDecimal actual = underTest.getAvailableHolidays();
        //THEN
        verify(calculator).calculateAvailableHolidays(PERSON_ID);
        verify(authenticatedUserService).getAuthenticatedPersonDetails();
        verifyNoMoreInteractions(calculator, holidayHistoryService, historySaveService, transformer, authenticatedUserService);
        assertEquals(EXPECTED, actual);
    }

    @Test
    void testCreateNewHolidayShouldAdd() {
        //GIVEN
        HolidayEventDto dto = createDto();
        HolidayHistory holidayHistory = createHolidayHistory();
        HolidayEvent holidayEvent = createHolidayEvent();
        when(holidayHistoryService.getHistoryByPersonId(PERSON_ID)).thenReturn(holidayHistory);
        when(authenticatedUserService.getAuthenticatedPersonDetails()).thenReturn(PERSON_ID);
        when(transformer.transform(dto)).thenReturn(holidayEvent);
        //WHEN
        verifyNoInteractions(calculator, holidayHistoryService, historySaveService, transformer, authenticatedUserService);
        underTest.createNewHoliday(dto);
        //THEN
        verify(holidayHistoryService).getHistoryByPersonId(PERSON_ID);
        verify(transformer).transform(dto);
        verify(authenticatedUserService).getAuthenticatedPersonDetails();
        verify(historySaveService).saveHistory(holidayHistory, holidayEvent, START_DATE.getYear());
        verifyNoMoreInteractions(calculator, holidayHistoryService, historySaveService, transformer, authenticatedUserService);
    }

    private HolidayEventDto createDto() {
        return HolidayEventDto.builder()
            .dadHoliday(false)
            .endDate(END_DATE)
            .startDate(START_DATE)
            .description(DESCRIPTION)
            .build();
    }

    private HolidayHistory createHolidayHistory() {
        return HolidayHistory.builder()
            .build();
    }

    private HolidayEvent createHolidayEvent() {
        return HolidayEvent.builder()
            .dadHoliday(false)
            .endDate(END_DATE)
            .startDate(START_DATE)
            .description(DESCRIPTION)
            .build();
    }

}
