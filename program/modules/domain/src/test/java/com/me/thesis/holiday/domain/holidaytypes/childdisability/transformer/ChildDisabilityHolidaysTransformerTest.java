package com.me.thesis.holiday.domain.holidaytypes.childdisability.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;
import com.me.thesis.holiday.dto.holidaytypes.ChildDisabilityHolidayDetailsDto;

/**
 * The type Child disability holidays transformer test.
 */
class ChildDisabilityHolidaysTransformerTest {

    private static final int CHILDREN_NUMBER = 1;
    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "CHILD_DISABILITY_HOLIDAY";
    private static final String DESCRIPTION = "days because of 1 child disability";
    private static final int DAYS = 20;

    @InjectMocks
    private ChildDisabilityHolidaysTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTestTransformShouldTransformDto() {
        //GIVEN
        ChildDisabilityHolidayDetailsDto dto = createDto();
        ChildDisabilityHolidays expected = createDomain();
        //WHEN
        ChildDisabilityHolidays actual = underTest.transform(dto);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private ChildDisabilityHolidayDetailsDto createDto() {
        return ChildDisabilityHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .childrenNumber(CHILDREN_NUMBER)
            .day(DAYS)
            .build();
    }

    private ChildDisabilityHolidays createDomain() {
        return ChildDisabilityHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .childrenNumber(CHILDREN_NUMBER)
            .day(DAYS)
            .build();
    }

    @Test
    void testTestTransformShouldTransformDomain() {
        //GIVEN
        ChildDisabilityHolidays domain = createDomain();
        ChildDisabilityHolidayDetailsDto expected = createDto();
        //WHEN
        ChildDisabilityHolidayDetailsDto actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
