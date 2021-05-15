package com.me.thesis.holiday.dal.nationalholiday.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.nationalholiday.domain.NationalHolidayEntity;
import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;

/**
 * The type National holiday entity transformer test.
 */
class NationalHolidayEntityTransformerTest {

    private static final long ID = 1111L;
    private static final LocalDate DATE = LocalDate.EPOCH;

    @InjectMocks
    private NationalHolidayEntityTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformEntity() {
        //GIVEN
        NationalHolidayEntity entity = createEntity();
        NationalHoliday expected = createDomain();
        //WHEN
        NationalHoliday actual = underTest.transform(entity);
        //THEN
        assertEquals(expected, actual);
    }

    private NationalHolidayEntity createEntity() {
        return NationalHolidayEntity.builder()
            .id(ID)
            .date(DATE)
            .build();
    }

    private NationalHoliday createDomain() {
        return NationalHoliday.builder()
            .id(ID)
            .date(DATE)
            .build();
    }

    @Test
    void testTestTransformShouldTransformDomain() {
        //GIVEN
        NationalHolidayEntity expected = createEntity();
        NationalHoliday domain = createDomain();
        //WHEN
        NationalHolidayEntity actual = underTest.transform(domain);
        //THEN
        assertEquals(expected, actual);
    }

}
