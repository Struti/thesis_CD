package com.me.thesis.holiday.domain.holidaytypes.dad.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidaytypes.dad.domain.DadHolidays;
import com.me.thesis.holiday.dto.holidaytypes.DadHolidayDetailsDto;

/**
 * The type Dad holidays transformer test.
 */
class DadHolidaysTransformerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "DAD_HOLIDAY";
    private static final String DESCRIPTION = "days because of birth, month limit";
    private static final int LIMIT = 200;
    private static final int DAYS = 20;

    @InjectMocks
    private DadHolidaysTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTestTransformShouldTransformDto() {
        //GIVEN
        DadHolidayDetailsDto dto = createDto();
        DadHolidays expected = createDomain();
        //WHEN
        DadHolidays actual = underTest.transform(dto);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private DadHolidayDetailsDto createDto() {
        return DadHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .limit(LIMIT)
            .build();
    }

    private DadHolidays createDomain() {
        return DadHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .limit(LIMIT)
            .build();
    }

    @Test
    void testTestTransformShouldTransformDomain() {
        //GIVEN
        DadHolidays domain = createDomain();
        DadHolidayDetailsDto expected = createDto();
        //WHEN
        DadHolidayDetailsDto actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
