package com.me.thesis.holiday.domain.holidaytypes.age.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;
import com.me.thesis.holiday.dto.holidaytypes.AgeHolidayDetailsDto;

/**
 * The type Age holidays transformer test.
 */
class AgeHolidaysTransformerTest {

    private static final int AGE = 1;
    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "AGE_HOLIDAY";
    private static final String DESCRIPTION = "days because of user age";
    private static final int DAYS = 20;

    @InjectMocks
    private AgeHolidaysTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTestTransformShouldTransformDto() {
        //GIVEN
        AgeHolidayDetailsDto dto = createDto();
        AgeBasedHolidays expected = createDomain();
        //WHEN
        AgeBasedHolidays actual = underTest.transform(dto);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private AgeHolidayDetailsDto createDto() {
        return AgeHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .age(AGE)
            .day(DAYS)
            .build();
    }

    private AgeBasedHolidays createDomain() {
        return AgeBasedHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .age(AGE)
            .day(DAYS)
            .build();
    }

    @Test
    void testTestTransformShouldTransformDomain() {
        //GIVEN
        AgeBasedHolidays domain = createDomain();
        AgeHolidayDetailsDto expected = createDto();
        //WHEN
        AgeHolidayDetailsDto actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
