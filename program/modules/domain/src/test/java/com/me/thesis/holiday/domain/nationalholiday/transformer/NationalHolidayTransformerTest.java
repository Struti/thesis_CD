package com.me.thesis.holiday.domain.nationalholiday.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;
import com.me.thesis.holiday.dto.nationalholiday.NationalHolidayDto;

class NationalHolidayTransformerTest {

    private static final LocalDate HOLIDAY = LocalDate.of(2020, 1, 1);

    @InjectMocks
    private NationalHolidayTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransform() {
        //GIVEN
        NationalHolidayDto dto = createDto();
        NationalHoliday expected = createNationalHoliday();
        //WHEN
        NationalHoliday actual = underTest.transform(dto);
        //THEN
        assertEquals(expected, actual);
    }

    private NationalHolidayDto createDto() {
        return NationalHolidayDto.builder()
            .date(HOLIDAY)
            .fix(true)
            .build();
    }

    private NationalHoliday createNationalHoliday() {
        return NationalHoliday.builder()
            .date(HOLIDAY)
            .fix(true)
            .build();
    }

}
