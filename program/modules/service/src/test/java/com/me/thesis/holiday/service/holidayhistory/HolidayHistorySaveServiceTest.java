package com.me.thesis.holiday.service.holidayhistory;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidayhistory.domain.EventRelatedHoliday;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayEvent;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;
import com.me.thesis.holiday.domain.holidayhistory.domain.YearHistory;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;

class HolidayHistorySaveServiceTest {

    private static final long ID = 1111L;
    private static final int YEAR_2020 = 2020;
    private static final int YEAR_2021 = 2021;
    private static final int YEAR_2022 = 2022;

    @InjectMocks
    private HolidayHistorySaveService underTest;

    @Mock
    private HolidayHistoryService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveHistoryShouldSaveEventRelatedHoliday() {
        //GIVEN
        HolidayHistory holidayHistoryInput = createHolidayHistoryInput();
        EventRelatedHoliday eventRelatedHistory = createEventRelatedHistory();
        HolidayHistory savedHistory = createHistoryWithExtraEventRelatedHistory(eventRelatedHistory);
        //WHEN
        verifyNoInteractions(service);
        underTest.saveHistory(holidayHistoryInput, eventRelatedHistory, YEAR_2021);
        //THEN
        verify(service).save(savedHistory);
        verifyNoMoreInteractions(service);
    }

    private HolidayHistory createHistoryWithExtraEventRelatedHistory(EventRelatedHoliday eventRelatedHistory) {
        return HolidayHistory.builder()
            .id(ID)
            .person(createPerson())
            .yearHistories(createYearHistoriesWithExtraEventRelated(eventRelatedHistory))
            .build();
    }

    private List<YearHistory> createYearHistoriesWithExtraEventRelated(EventRelatedHoliday eventRelatedHistory) {
        List<EventRelatedHoliday> eventRelatedHistories = createEventRelatedHistories();
        List<EventRelatedHoliday> eventRelatedHolidays_2 = new ArrayList<>(List.copyOf(eventRelatedHistories));
        eventRelatedHolidays_2.add(eventRelatedHistory);
        List<HolidayEvent> holidayEvents = new ArrayList<>(createHolidayEvents());
        return List.of(createYearHistory(YEAR_2020, eventRelatedHistories, holidayEvents), createYearHistory(YEAR_2021, eventRelatedHolidays_2, holidayEvents));

    }

    private List<EventRelatedHoliday> createEventRelatedHistories() {
        return List.of(createEventRelatedHistory());
    }

    private EventRelatedHoliday createEventRelatedHistory() {
        return EventRelatedHoliday.builder()
            .build();
    }

    private List<HolidayEvent> createHolidayEvents() {
        return List.of(createHolidayEvent());
    }

    private HolidayEvent createHolidayEvent() {
        return HolidayEvent.builder()
            .build();
    }

    private HolidayHistory createHolidayHistoryInput() {
        return HolidayHistory.builder()
            .id(ID)
            .person(createPerson())
            .yearHistories(createYearHistories())
            .build();
    }

    private List<YearHistory> createYearHistories() {
        List<EventRelatedHoliday> eventRelatedHistories = createEventRelatedHistories();
        List<HolidayEvent> holidayEvents = createHolidayEvents();
        return List.of(createYearHistory(YEAR_2020, eventRelatedHistories, holidayEvents), createYearHistory(YEAR_2021, eventRelatedHistories, holidayEvents));
    }

    private YearHistory createYearHistory(int year, List<EventRelatedHoliday> eventRelated, List<HolidayEvent> holidayEvent) {
        return YearHistory.builder()
            .year(year)
            .eventRelatedHolidays(eventRelated)
            .holidayEvents(holidayEvent)
            .build();
    }

    private PersonDetails createPerson() {
        return PersonDetails.builder()
            .build();
    }

    @Test
    void testTestSaveHistoryShouldSaveHolidayEvent() {
        //GIVEN
        HolidayHistory holidayHistoryInput = createHolidayHistoryInput();
        HolidayEvent holidayEvent = createHolidayEvent();
        HolidayHistory savedHistory = createHistoryWithExtraEvent(holidayEvent);
        //WHEN
        verifyNoInteractions(service);
        underTest.saveHistory(holidayHistoryInput, holidayEvent, YEAR_2021);
        //THEN
        verify(service).save(savedHistory);
        verifyNoMoreInteractions(service);
    }

    private HolidayHistory createHistoryWithExtraEvent(HolidayEvent holidayEvent) {
        return HolidayHistory.builder()
            .id(ID)
            .person(createPerson())
            .yearHistories(createYearHistoriesWithExtraEvent(holidayEvent))
            .build();
    }

    private List<YearHistory> createYearHistoriesWithExtraEvent(HolidayEvent holidayEvent) {
        List<EventRelatedHoliday> eventRelatedHistories = createEventRelatedHistories();
        List<HolidayEvent> holidayEvents = new ArrayList<>(createHolidayEvents());
        List<HolidayEvent> holidayEvents_2 = new ArrayList<>(List.copyOf(holidayEvents));
        holidayEvents_2.add(holidayEvent);
        return List.of(createYearHistory(YEAR_2020, eventRelatedHistories, holidayEvents), createYearHistory(YEAR_2021, eventRelatedHistories, holidayEvents_2));
    }

    @Test
    void testTestSaveHistory1ShouldSaveYearHistory() {
        //GIVEN
        HolidayHistory holidayHistoryInput = createHolidayHistoryInput();
        YearHistory yearHistory = createYearHistory(YEAR_2022, new ArrayList<>(), new ArrayList<>());
        HolidayHistory savedHoliday = createSavedHistoryWithExtraYear();
        //WHEN
        verifyNoInteractions(service);
        underTest.saveHistory(holidayHistoryInput, yearHistory, YEAR_2022);
        //THEN
        verify(service).save(savedHoliday);
        verifyNoMoreInteractions(service);
    }

    private HolidayHistory createSavedHistoryWithExtraYear() {
        return HolidayHistory.builder()
            .id(ID)
            .person(createPerson())
            .yearHistories(createYearHistoriesWithExtraYear())
            .build();
    }

    private List<YearHistory> createYearHistoriesWithExtraYear() {
        List<EventRelatedHoliday> eventRelatedHistories = createEventRelatedHistories();
        List<HolidayEvent> holidayEvents = createHolidayEvents();
        return List.of(createYearHistory(YEAR_2020, eventRelatedHistories, holidayEvents), createYearHistory(YEAR_2021, eventRelatedHistories, holidayEvents),
            createYearHistory(YEAR_2022, new ArrayList<>(), new ArrayList<>()));
    }

}
