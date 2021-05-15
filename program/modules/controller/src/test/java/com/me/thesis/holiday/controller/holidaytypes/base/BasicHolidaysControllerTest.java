package com.me.thesis.holiday.controller.holidaytypes.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.domain.holidaytypes.basic.transformer.BasicHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.BasicHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.BasicHolidaysService;

/**
 * The type Basic holidays controller test.
 */
class BasicHolidaysControllerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "BASIC_HOLIDAY";
    private static final String DESCRIPTION = "basic holidays";
    private static final int DAYS = 20;

    @InjectMocks
    private BasicHolidaysController underTest;

    @Mock
    private BasicHolidaysService service;

    @Mock
    private BasicHolidaysTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAgeHolidayShouldReturnWithList() {
        //GIVEN
        List<BasicHolidays> expected = createDomains();
        given(service.getAllBasicHolidayDetails()).willReturn(expected);
        //WHEN
        verifyNoInteractions(service, transformer);
        List<BasicHolidays> actual = underTest.getAllBasicHolidayDetails();
        //THEN
        verify(service).getAllBasicHolidayDetails();
        verifyNoMoreInteractions(service, transformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<BasicHolidays> createDomains() {
        return List.of(createDomain());
    }

    private BasicHolidays createDomain() {
        return BasicHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    private BasicHolidayDetailsDto createDto() {
        return BasicHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveBasicHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(service, transformer);
        BasicHolidayDetailsDto dto = createDto();
        BasicHolidays domain = createDomain();
        given(transformer.transform(dto)).willReturn(domain);
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.saveBasicHoliday(dto);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(dto);
        inOrder.verify(service, times(1))
            .saveBasicdHoliday(domain);
        verifyNoMoreInteractions(service, transformer);
    }

    @Test
    void testDeleteBasicHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service);
        underTest.deleteBasicHoliday(HOLIDAY_ID);
        //THEN
        verify(service, times(1)).deleteBasicHolidayDetails(HOLIDAY_ID);
        verifyNoMoreInteractions(service);
    }

}
