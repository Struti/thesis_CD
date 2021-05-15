package com.me.thesis.holiday.domain.holidaytypes.basic.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.dto.holidaytypes.BasicHolidayDetailsDto;

/**
 * The type Basic holidays transformer test.
 */
class BasicHolidaysTransformerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "BASIC_HOLIDAY";
    private static final String DESCRIPTION = "basic holidays";
    private static final int DAYS = 20;

    @InjectMocks
    private BasicHolidaysTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTestTransformShouldTransformDto() {
        //GIVEN
        BasicHolidayDetailsDto dto = createDto();
        BasicHolidays expected = createDomain();
        //WHEN
        BasicHolidays actual = underTest.transform(dto);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private BasicHolidayDetailsDto createDto() {
        return BasicHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    private BasicHolidays createDomain() {
        return BasicHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    @Test
    void testTestTransformShouldTransformDomain() {
        //GIVEN
        BasicHolidays domain = createDomain();
        BasicHolidayDetailsDto expected = createDto();
        //WHEN
        BasicHolidayDetailsDto actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
