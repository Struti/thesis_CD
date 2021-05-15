package com.me.thesis.holiday.controller.holidaytypes.maternity;

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

import com.me.thesis.holiday.domain.holidaytypes.maternity.domain.MaternityHoliday;
import com.me.thesis.holiday.domain.holidaytypes.maternity.transformer.MaternityHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.MaternityHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.MaternityHolidaysService;

/**
 * The type Maternity holidays controller test.
 */
class MaternityHolidaysControllerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "MOM_GIVE_BIRTH_HOLIDAY";
    private static final String DESCRIPTION = "days before expected date";
    private static final int DAYS = 20;

    @InjectMocks
    private MaternityHolidaysController underTest;

    @Mock
    private MaternityHolidaysService service;

    @Mock
    private MaternityHolidaysTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAgeHolidayShouldReturnWithList() {
        //GIVEN
        List<MaternityHoliday> expected = createDomains();
        given(service.getAllMaternityHolidays()).willReturn(expected);
        //WHEN
        verifyNoInteractions(service);
        List<MaternityHoliday> actual = underTest.getAllMaternityHolidays();
        //THEN
        verify(service).getAllMaternityHolidays();
        verifyNoMoreInteractions(service);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<MaternityHoliday> createDomains() {
        return List.of(createDomain());
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
    void testSaveMaternityHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(service, transformer);
        MaternityHolidayDetailsDto dto = createDto();
        MaternityHoliday domain = createDomain();
        given(transformer.transform(dto)).willReturn(domain);
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.saveMaternityHoliday(dto);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(dto);
        inOrder.verify(service, times(1))
            .saveMaternityHoliday(domain);
        verifyNoMoreInteractions(service, transformer);
    }

    @Test
    void testDeleteMaternityHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service);
        underTest.deleteMaternityHoliday(HOLIDAY_ID);
        //THEN
        verify(service, times(1)).deleteMaternityHoliday(HOLIDAY_ID);
        verifyNoMoreInteractions(service);
    }

}
