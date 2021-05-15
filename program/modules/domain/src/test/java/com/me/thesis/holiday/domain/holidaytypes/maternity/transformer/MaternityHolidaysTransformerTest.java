package com.me.thesis.holiday.domain.holidaytypes.maternity.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidaytypes.maternity.domain.MaternityHoliday;
import com.me.thesis.holiday.dto.holidaytypes.MaternityHolidayDetailsDto;

/**
 * The type Maternity holidays transformer test.
 */
class MaternityHolidaysTransformerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "MOM_GIVE_BIRTH_HOLIDAY";
    private static final String DESCRIPTION = "days before expected date";
    private static final int DAYS = 20;

    @InjectMocks
    private MaternityHolidaysTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTestTransformShouldTransformDto() {
        //GIVEN
        MaternityHolidayDetailsDto dto = createDto();
        MaternityHoliday expected = createDomain();
        //WHEN
        MaternityHoliday actual = underTest.transform(dto);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private MaternityHolidayDetailsDto createDto() {
        return MaternityHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .beforeDate(DAYS)
            .afterDate(DAYS)
            .build();
    }

    private MaternityHoliday createDomain() {
        return MaternityHoliday.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .beforeDays(DAYS)
            .afterDays(DAYS)
            .build();
    }

    @Test
    void testTestTransformShouldTransformDomain() {
        //GIVEN
        MaternityHoliday domain = createDomain();
        MaternityHolidayDetailsDto expected = createDto();
        //WHEN
        MaternityHolidayDetailsDto actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
