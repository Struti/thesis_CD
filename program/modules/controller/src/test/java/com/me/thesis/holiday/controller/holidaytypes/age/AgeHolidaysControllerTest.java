package com.me.thesis.holiday.controller.holidaytypes.age;

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

import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;
import com.me.thesis.holiday.domain.holidaytypes.age.transformer.AgeHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.AgeHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.AgeHolidaysService;

/**
 * The type Age holidays controller test.
 */
class AgeHolidaysControllerTest {

    private static final int AGE = 1;
    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "AGE_HOLIDAY";
    private static final String DESCRIPTION = "days because of user age";
    private static final int DAYS = 20;

    @InjectMocks
    private AgeHolidaysController underTest;

    @Mock
    private AgeHolidaysService service;

    @Mock
    private AgeHolidaysTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAgeHolidayShouldReturnWithList() {
        //GIVEN
        List<AgeBasedHolidays> expected = createDomains();
        given(service.getAllAgeBasedHolidayDetails()).willReturn(expected);
        //WHEN
        verifyNoInteractions(service, transformer);
        List<AgeBasedHolidays> actual = underTest.getAllAgeHolidayDetails();
        //THEN
        verify(service).getAllAgeBasedHolidayDetails();
        verifyNoMoreInteractions(service, transformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<AgeBasedHolidays> createDomains() {
        return List.of(createDomain());
    }

    private AgeBasedHolidays createDomain() {
        return AgeBasedHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .age(AGE)
            .day(DAYS)
            .build();
    }

    @Test
    void testDeleteAgeHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service);
        underTest.deleteAgeHoliday(HOLIDAY_ID);
        //THEN
        verify(service, times(1)).deleteAgeBasedHolidayDetails(HOLIDAY_ID);
        verifyNoMoreInteractions(service);
    }

    private AgeHolidayDetailsDto createDto() {
        return AgeHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .age(AGE)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveAgeHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(service, transformer);
        AgeHolidayDetailsDto dto = createDto();
        AgeBasedHolidays domain = createDomain();
        given(transformer.transform(dto)).willReturn(domain);
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.saveAgeHoliday(dto);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(dto);
        inOrder.verify(service, times(1))
            .saveAgeBasedHoliday(domain);
        verifyNoMoreInteractions(service, transformer);
    }

}
