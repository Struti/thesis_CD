package com.me.thesis.holiday.domain.holidaytypes.useddisability.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.transformer.UserDisabilityHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.UserDisabilityHolidayDetailsDto;

/**
 * The type User disability holidays transformer test.
 */
class UserDisabilityHolidaysTransformerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "USER_DISABILITY_HOLIDAY";
    private static final String DESCRIPTION = "days because user have some disability";
    private static final int DAY = 20;

    @InjectMocks
    private UserDisabilityHolidaysTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTestTransformShouldTransformDto() {
        //GIVEN
        UserDisabilityHolidayDetailsDto dto = createDto();
        UserDisabilityHolidays expected = createDomain();
        //WHEN
        UserDisabilityHolidays actual = underTest.transform(dto);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private UserDisabilityHolidayDetailsDto createDto() {
        return UserDisabilityHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAY)
            .build();
    }

    private UserDisabilityHolidays createDomain() {
        return UserDisabilityHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAY)
            .build();
    }

    @Test
    void testTestTransformShouldTransformDomain() {
        //GIVEN
        UserDisabilityHolidays domain = createDomain();
        UserDisabilityHolidayDetailsDto expected = createDto();
        //WHEN
        UserDisabilityHolidayDetailsDto actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
