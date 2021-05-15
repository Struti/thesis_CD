package com.me.thesis.holiday.domain.holidaytypes.death.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidaytypes.death.domain.DeathHolidays;
import com.me.thesis.holiday.dto.holidaytypes.DeathHolidayDetailsDto;

/**
 * The type Death holidays transformer test.
 */
class DeathHolidaysTransformerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "DEATH_HOLIDAY";
    private static final String DESCRIPTION = "death cause holidays";
    private static final int DAYS = 20;

    @InjectMocks
    private DeathHolidaysTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTestTransformShouldTransformDto() {
        //GIVEN
        DeathHolidayDetailsDto dto = createDto();
        DeathHolidays expected = createDomain();
        //WHEN
        DeathHolidays actual = underTest.transform(dto);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private DeathHolidayDetailsDto createDto() {
        return DeathHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    private DeathHolidays createDomain() {
        return DeathHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    @Test
    void testTestTransformShouldTransformDomain() {
        //GIVEN
        DeathHolidays domain = createDomain();
        DeathHolidayDetailsDto expected = createDto();
        //WHEN
        DeathHolidayDetailsDto actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
