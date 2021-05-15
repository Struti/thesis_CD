package com.me.thesis.holiday.domain.holidayhistory.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayEvent;
import com.me.thesis.holiday.dto.holidayhistory.HolidayEventDto;

class HolidayEventDomainTransformerTest {

    private static final long PERSON_ID = 1111L;
    private static final String DESCRIPTION = "desc";
    private static final LocalDate START_DATE = LocalDate.MIN;
    private static final LocalDate END_DATE = LocalDate.MAX;

    @InjectMocks
    private HolidayEventDomainTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShould() {
        //GIVEN
        HolidayEventDto dto = createDto();
        HolidayEvent expected = createDomain();
        //WHEN
        HolidayEvent actual = underTest.transform(dto);
        //THEN
        assertEquals(expected, actual);
    }

    private HolidayEventDto createDto() {
        return HolidayEventDto.builder()
            .description(DESCRIPTION)
            .startDate(START_DATE)
            .endDate(END_DATE)
            .dadHoliday(false)
            .build();
    }

    private HolidayEvent createDomain() {
        return HolidayEvent.builder()
            .description(DESCRIPTION)
            .startDate(START_DATE)
            .endDate(END_DATE)
            .dadHoliday(false)
            .build();
    }

}
