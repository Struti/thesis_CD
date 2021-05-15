package com.me.thesis.holiday.controller.nationalholiday;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;
import com.me.thesis.holiday.domain.nationalholiday.transformer.NationalHolidayTransformer;
import com.me.thesis.holiday.dto.nationalholiday.NationalHolidayDto;
import com.me.thesis.holiday.service.nationalholiday.NationalHolidayService;

/**
 * The type National holiday controller test.
 */
class NationalHolidayControllerTest {

    private static final long ID = 11111111L;
    private static final LocalDate DATE = LocalDate.EPOCH;

    @InjectMocks
    private NationalHolidayController underTest;

    @Mock
    private NationalHolidayService service;

    @Mock
    private NationalHolidayTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNationalHolidaysShould() {
        //GIVEN
        List<NationalHoliday> expected = createNationalHolidays();
        when(service.getNationalHolidays()).thenReturn(expected);
        //WHEN
        verifyNoInteractions(service, transformer);
        List<NationalHoliday> actual = underTest.getNationalHolidays();
        //THEN
        verify(service).getNationalHolidays();
        verifyNoMoreInteractions(service, transformer);
        assertEquals(expected, actual);
    }

    @Test
    void testSaveAllShouldSaveList() {
        //GIVEN
        List<NationalHolidayDto> dtos = createDtos();
        NationalHoliday nationalHoliday = createNationalHoliday();
        when(transformer.transform(dtos.get(0))).thenReturn(nationalHoliday);
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.saveAll(dtos);
        //THEN
        verify(transformer).transform(dtos.get(0));
        verify(service).saveNationalHolidays(List.of(nationalHoliday));
        verifyNoMoreInteractions(service, transformer);
    }

    private List<NationalHolidayDto> createDtos() {
        return List.of(createDto());
    }

    private NationalHoliday createNationalHoliday() {
        return NationalHoliday.builder()
            .id(ID)
            .date(DATE)
            .fix(true)
            .build();
    }

    private NationalHolidayDto createDto() {
        return NationalHolidayDto.builder()
            .id(ID)
            .date(DATE)
            .fix(true)
            .build();
    }

    @Test
    void testSaveShouldSave() {
        //GIVEN
        NationalHolidayDto dto = createDto();
        NationalHoliday domain = createNationalHoliday();
        when(transformer.transform(dto)).thenReturn(domain);
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.save(dto);
        //THEN
        verify(transformer).transform(dto);
        verify(service).saveNationalHoliday(domain);
        verifyNoMoreInteractions(service, transformer);
    }

    private List<NationalHoliday> createNationalHolidays() {
        return List.of(createNationalHoliday());
    }

    @Test
    void testDeleteShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service, transformer);
        underTest.delete(ID);
        //THEN
        verify(service).deleteNationalHoliday(ID);
        verifyNoMoreInteractions(service, transformer);
    }

}
