package com.me.thesis.holiday.controller.holidaytypes.dad;

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

import com.me.thesis.holiday.domain.holidaytypes.dad.domain.DadHolidays;
import com.me.thesis.holiday.domain.holidaytypes.dad.transformer.DadHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.DadHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.DadHolidaysService;

/**
 * The type Dad holidays controller test.
 */
class DadHolidaysControllerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "DAD_HOLIDAY";
    private static final String DESCRIPTION = "days because of birth, month limit";
    private static final int LIMIT = 200;
    private static final int DAYS = 20;

    @InjectMocks
    private DadHolidaysController underTest;

    @Mock
    private DadHolidaysService service;

    @Mock
    private DadHolidaysTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAgeHolidayShouldReturnWithList() {
        //GIVEN
        List<DadHolidays> expected = createDomains();
        given(service.getAllDadHolidays()).willReturn(expected);
        //WHEN
        verifyNoInteractions(service, transformer);
        List<DadHolidays> actual = underTest.getAllDadHolidays();
        //THEN
        verify(service).getAllDadHolidays();
        verifyNoMoreInteractions(service, transformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<DadHolidays> createDomains() {
        return List.of(createDomain());
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

    private DadHolidayDetailsDto createDto() {
        return DadHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .limit(LIMIT)
            .build();
    }

    @Test
    void testSaveDadHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(service, transformer);
        DadHolidayDetailsDto dto = createDto();
        DadHolidays domain = createDomain();
        given(transformer.transform(dto)).willReturn(domain);
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.saveDadHoliday(dto);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(dto);
        inOrder.verify(service, times(1))
            .saveDadHoliday(domain);
        verifyNoMoreInteractions(service, transformer);
    }

    @Test
    void testDeleteDadHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service);
        underTest.deleteDadHoliday(HOLIDAY_ID);
        //THEN
        verify(service, times(1)).deleteDadHoliday(HOLIDAY_ID);
        verifyNoMoreInteractions(service);
    }

}
