package com.me.thesis.holiday.domain.holidayplan.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidayplan.factory.PlanFactory;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.domain.holidaytypes.basic.transformer.BasicHolidaysTransformer;
import com.me.thesis.holiday.dto.holidayplan.HolidayType;
import com.me.thesis.holiday.dto.holidayplan.PlanDto;
import com.me.thesis.holiday.dto.holidaytypes.BasicHolidayDetailsDto;

/**
 * The type Plan factory test.
 */
class PlanFactoryTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "TYPE";
    private static final int DAYS = 20;
    private static final HolidayType AGE_HOLIDAY_TYPE = HolidayType.AGE;
    private static final HolidayType CHILD_HOLIDAY_TYPE = HolidayType.CHILD;
    private static final HolidayType CHILD_DISABILITY_HOLIDAY_TYPE = HolidayType.CHILD_DISABILITY;
    private static final HolidayType USER_DISABILITY_HOLIDAY_TYPE = HolidayType.USER_DISABILITY;
    private static final List<HolidayType> FIX_HOLIDAYS = List.of(AGE_HOLIDAY_TYPE, CHILD_HOLIDAY_TYPE, CHILD_DISABILITY_HOLIDAY_TYPE, USER_DISABILITY_HOLIDAY_TYPE);

    @InjectMocks
    private PlanFactory underTest;

    @Mock
    private BasicHolidaysTransformer basicHolidaysTransformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicHolidaysShouldTransformWithoutFixedHolidays() {
        //GIVE
        PlanDto dto = createDtoWithoutFixedHolidays();
        Plan expected = createDomainWithoutFixedHolidays();
        when(basicHolidaysTransformer.transform(dto.getBasicHolidays())).thenReturn(expected.getBasicHolidays());
        //WHEN
        verifyNoInteractions(basicHolidaysTransformer);
        Plan actual = underTest.create(dto);
        //THEN
        verify(basicHolidaysTransformer).transform(dto.getBasicHolidays());
        verifyNoMoreInteractions(basicHolidaysTransformer);
        assertEquals(expected, actual);
    }

    private PlanDto createDtoWithoutFixedHolidays() {
        return PlanDto.builder()
            .basicHolidays(createBasicHolidaysDto())
            .build();
    }

    private Plan createDomainWithoutFixedHolidays() {
        return Plan.builder()
            .basicHolidays(createBasicHolidaysDomain())
            .build();
    }

    private BasicHolidayDetailsDto createBasicHolidaysDto() {
        return BasicHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .day(DAYS)
            .build();
    }

    private BasicHolidays createBasicHolidaysDomain() {
        return BasicHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .day(DAYS)
            .build();
    }

    @Test
    void testGetBasicHolidaysShouldTransformWithFixedHolidays() {
        //GIVE
        PlanDto dto = createDtoWithFixedHolidays();
        Plan expected = createDomainWithFixedHolidays();
        when(basicHolidaysTransformer.transform(dto.getBasicHolidays())).thenReturn(expected.getBasicHolidays());
        //WHEN
        verifyNoInteractions(basicHolidaysTransformer);
        Plan actual = underTest.create(dto);
        //THEN
        verify(basicHolidaysTransformer).transform(dto.getBasicHolidays());
        verifyNoMoreInteractions(basicHolidaysTransformer);
        assertEquals(expected, actual);
    }

    private PlanDto createDtoWithFixedHolidays() {
        return PlanDto.builder()
            .basicHolidays(createBasicHolidaysDto())
            .fixHolidays(FIX_HOLIDAYS)
            .build();
    }

    private Plan createDomainWithFixedHolidays() {
        return Plan.builder()
            .basicHolidays(createBasicHolidaysDomain())
            .fixHolidays(FIX_HOLIDAYS)
            .build();
    }

}
