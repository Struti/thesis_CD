package com.me.thesis.holiday.domain.holidaytypes.children.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;
import com.me.thesis.holiday.dto.holidaytypes.ChildrenHolidayDetailsDto;

/**
 * The type Children holidays transformer test.
 */
class ChildrenHolidaysTransformerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "CHILD_HOLIDAY";
    private static final String DESCRIPTION = "days because children";
    private static final int DAYS = 20;
    private static final int CHILDREN = 3;

    @InjectMocks
    private ChildrenHolidaysTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTestTransformShouldTransformDto() {
        //GIVEN
        ChildrenHolidayDetailsDto dto = createDto();
        ChildrenHolidays expected = createDomain();
        //WHEN
        ChildrenHolidays actual = underTest.transform(dto);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private ChildrenHolidayDetailsDto createDto() {
        return ChildrenHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .children(CHILDREN)
            .day(DAYS)
            .build();
    }

    private ChildrenHolidays createDomain() {
        return ChildrenHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .children(CHILDREN)
            .day(DAYS)
            .build();
    }

    @Test
    void testTestTransformShouldTransformDomain() {
        //GIVEN
        ChildrenHolidays domain = createDomain();
        ChildrenHolidayDetailsDto expected = createDto();
        //WHEN
        ChildrenHolidayDetailsDto actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

}
