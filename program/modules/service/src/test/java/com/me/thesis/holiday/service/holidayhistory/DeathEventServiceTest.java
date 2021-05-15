package com.me.thesis.holiday.service.holidayhistory;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidayhistory.domain.EventRelatedHoliday;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidaytypes.EventRelatedHolidayType;
import com.me.thesis.holiday.domain.holidaytypes.death.domain.DeathHolidays;
import com.me.thesis.holiday.dto.holidayhistory.DeathEventDto;
import com.me.thesis.holiday.service.holidaytypes.DeathHolidaysService;

class DeathEventServiceTest {

    public static final long ID = 1111L;
    public static final LocalDate EVENT_DATE = LocalDate.EPOCH;
    public static final int DAY = 10;
    @InjectMocks
    private DeathEventService underTest;

    @Mock
    private HolidayHistorySaveService historySaveService;

    @Mock
    private DeathHolidaysService deathHolidaysService;

    @Mock
    private HolidayHistoryService holidayHistoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEventShould() {
        //GIVEN
        DeathEventDto dto = createDto();
        HolidayHistory history = createHistory();
        when(deathHolidaysService.getAllDeathHolidayDetails()).thenReturn(List.of(createDeathHolidays()));
        when(holidayHistoryService.getHistoryByPersonId(ID)).thenReturn(history);
        //WHEN
        verifyNoInteractions(historySaveService, deathHolidaysService, holidayHistoryService);
        underTest.addEvent(dto);
        //THEN
        verify(holidayHistoryService).getHistoryByPersonId(ID);
        verify(deathHolidaysService).getAllDeathHolidayDetails();
        verify(historySaveService).saveHistory(history, createEventRelatedHoliday(), EVENT_DATE.getYear());
        verifyNoMoreInteractions(historySaveService, deathHolidaysService, holidayHistoryService);
    }

    private DeathEventDto createDto() {
        return DeathEventDto.builder()
            .personId(ID)
            .eventDate(EVENT_DATE)
            .build();
    }

    private HolidayHistory createHistory() {
        return HolidayHistory.builder()
            .id(ID)
            .yearHistories(new ArrayList<>())
            .build();
    }

    private DeathHolidays createDeathHolidays() {
        return DeathHolidays.builder()
            .id(ID)
            .day(DAY)
            .build();
    }

    private EventRelatedHoliday createEventRelatedHoliday() {
        return EventRelatedHoliday.builder()
            .holidayType(EventRelatedHolidayType.DEATH)
            .eventDate(EVENT_DATE)
            .days(DAY)
            .build();
    }

}
