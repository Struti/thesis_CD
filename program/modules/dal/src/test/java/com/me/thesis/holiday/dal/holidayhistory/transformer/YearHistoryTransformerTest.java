package com.me.thesis.holiday.dal.holidayhistory.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;

import com.google.common.io.Resources;
import com.me.thesis.holiday.domain.holidayhistory.domain.EventRelatedHoliday;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayEvent;
import com.me.thesis.holiday.domain.holidayhistory.domain.YearHistory;
import com.me.thesis.holiday.domain.holidaytypes.EventRelatedHolidayType;

class YearHistoryTransformerTest {

    private static final int YEAR_2020 = 2020;
    private static final int YEAR_2021 = 2021;
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final BigDecimal FIX_HOLIDAYS_FOR_THE_YEAR = BigDecimal.TEN;
    private static final EventRelatedHolidayType HOLIDAY_TYPE = EventRelatedHolidayType.DAD;
    private static final LocalDate EVENT_DATE = LocalDate.of(2021, 3, 30);
    private static final LocalDate EXPIRATION = LocalDate.of(2021, 4, 10);
    private static final String DESCRIPTION = "desc";
    private static final LocalDate END_DATE = LocalDate.of(2020, 5, 20);
    private static final LocalDate START_DATE = LocalDate.of(2020, 3, 4);

    @InjectMocks
    private YearHistoryTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldReturnJson() throws IOException, JSONException {
        //GIVEN
        List<YearHistory> yearHistories = createYearHistories();
        URL resource = this.getClass()
            .getResource("/holidayhistory/yearhistory/expected/year_histories.json");
        String expected = Resources.toString(resource, UTF_8);
        //WHEN
        String actual = underTest.transform(yearHistories);
        //THEN
        System.out.println(actual);
        JSONAssert.assertEquals(expected, actual, true);
    }

    private List<YearHistory> createYearHistories() {
        return List.of(create2020History(), create2021History());
    }

    private YearHistory create2021History() {
        return YearHistory.builder()
            .year(YEAR_2021)
            .fixHolidaysForTheYear(FIX_HOLIDAYS_FOR_THE_YEAR)
            .eventRelatedHolidays(createEventRelatedHolidays())
            .build();
    }

    private List<EventRelatedHoliday> createEventRelatedHolidays() {
        return List.of(createEventRelatedHoliday(), createEventRelatedHolidayWithExpiration());
    }

    private EventRelatedHoliday createEventRelatedHolidayWithExpiration() {
        return EventRelatedHoliday.builder()
            .holidayType(HOLIDAY_TYPE)
            .eventDate(EVENT_DATE)
            .expiration(EXPIRATION)
            .build();
    }

    private EventRelatedHoliday createEventRelatedHoliday() {
        return EventRelatedHoliday.builder()
            .holidayType(HOLIDAY_TYPE)
            .eventDate(EVENT_DATE)
            .build();
    }

    private YearHistory create2020History() {
        return YearHistory.builder()
            .year(YEAR_2020)
            .fixHolidaysForTheYear(FIX_HOLIDAYS_FOR_THE_YEAR)
            .eventRelatedHolidays(createEventRelatedHolidays())
            .eventRelatedHolidays(createEventRelatedHolidays())
            .holidayEvents(createHolidayEvents())
            .build();
    }

    private List<HolidayEvent> createHolidayEvents() {
        return List.of(createHolidayEvent());
    }

    private HolidayEvent createHolidayEvent() {
        return HolidayEvent.builder()
            .description(DESCRIPTION)
            .startDate(START_DATE)
            .endDate(END_DATE)
            .build();
    }

    @Test
    void testTestTransformShouldReturnObject() throws IOException {
        //GIVEN
        URL resource = this.getClass()
            .getResource("/holidayhistory/yearhistory/mockdata/year_histories.json");
        String yearHistories = Resources.toString(resource, UTF_8);
        List<YearHistory> expected = createYearHistories();
        //WHEN
        List<YearHistory> actual = underTest.transform(yearHistories);
        //THEN
        assertEquals(expected, actual);
    }

}
