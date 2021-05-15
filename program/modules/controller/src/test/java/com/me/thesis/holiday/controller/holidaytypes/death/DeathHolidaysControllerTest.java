package com.me.thesis.holiday.controller.holidaytypes.death;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidaytypes.death.domain.DeathHolidays;
import com.me.thesis.holiday.domain.holidaytypes.death.transformer.DeathHolidaysTransformer;
import com.me.thesis.holiday.dto.holidaytypes.DeathHolidayDetailsDto;
import com.me.thesis.holiday.service.holidaytypes.DeathHolidaysService;

/**
 * The type Death holidays controller test.
 */
class DeathHolidaysControllerTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "DEATH_HOLIDAY";
    private static final String DESCRIPTION = "death cause holidays";
    private static final int DAYS = 20;

    @InjectMocks
    private DeathHolidaysController underTest;

    @Mock
    private DeathHolidaysService service;

    @Mock
    private DeathHolidaysTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAgeHolidayShouldReturnWithList() {
        //GIVEN
        List<DeathHolidays> expected = createDomains();
        given(service.getAllDeathHolidayDetails()).willReturn(expected);
        //WHEN
        verifyNoInteractions(service);
        List<DeathHolidays> actual = underTest.getAllDeathHolidayDetails();
        //THEN
        verify(service).getAllDeathHolidayDetails();
        verifyNoMoreInteractions(service);
        assertEquals(expected, actual);
    }

    private List<DeathHolidays> createDomains() {
        return List.of(createDomain());
    }

    private DeathHolidays createDomain() {
        return DeathHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    private DeathHolidayDetailsDto createDto() {
        return DeathHolidayDetailsDto.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveDeathHolidayShouldSave() {
        //GIVEN
        DeathHolidayDetailsDto dto = createDto();
        DeathHolidays domain = createDomain();
        given(transformer.transform(dto)).willReturn(domain);
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.saveDeathHoliday(dto);
        //THEN
        verify(transformer).transform(dto);
        verify(service).saveDeathHoliday(domain);
        verifyNoMoreInteractions(service, transformer);
    }

    @Test
    void testDeleteDeathHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service);
        underTest.deleteDeathHoliday(HOLIDAY_ID);
        //THEN
        verify(service).deleteDeathHolidayDetails(HOLIDAY_ID);
        verifyNoMoreInteractions(service);
    }

}
