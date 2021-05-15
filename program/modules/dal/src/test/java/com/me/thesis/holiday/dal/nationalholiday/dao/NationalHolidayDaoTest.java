package com.me.thesis.holiday.dal.nationalholiday.dao;

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

import com.me.thesis.holiday.dal.nationalholiday.domain.NationalHolidayEntity;
import com.me.thesis.holiday.dal.nationalholiday.repository.NationalHolidayRepository;
import com.me.thesis.holiday.dal.nationalholiday.transformer.NationalHolidayEntityTransformer;
import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;

/**
 * The type National holiday dao test.
 */
class NationalHolidayDaoTest {

    private static final LocalDate DATE = LocalDate.EPOCH;
    private static final long ID = 11111L;

    @InjectMocks
    private NationalHolidayDao underTest;

    @Mock
    private NationalHolidayRepository repository;

    @Mock
    private NationalHolidayEntityTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNationalHolidaysShouldReturnList() {
        //GIVEN
        List<NationalHolidayEntity> entities = createEntities();
        List<NationalHoliday> expected = createDomains();
        when(repository.findAll()).thenReturn(entities);
        when(transformer.transform(entities.get(0))).thenReturn(expected.get(0));
        //WHEN
        verifyNoInteractions(repository, transformer);
        List<NationalHoliday> actual = underTest.getNationalHolidays();
        //THEN
        verify(repository).findAll();
        verify(transformer).transform(entities.get(0));
        verifyNoMoreInteractions(repository, transformer);
        assertEquals(expected, actual);
    }

    private List<NationalHolidayEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<NationalHoliday> createDomains() {
        return List.of(createDomain());
    }

    private NationalHolidayEntity createEntity() {
        return NationalHolidayEntity.builder()
            .build();
    }

    private NationalHoliday createDomain() {
        return NationalHoliday.builder()
            .build();
    }

    @Test
    void testDeleteNationalHolidaysShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(repository, transformer);
        underTest.deleteEarlierNationalHolidays(DATE);
        //THEN
        verify(repository).deleteAllByDateBefore(DATE);
        verifyNoMoreInteractions(repository, transformer);
    }

    @Test
    void testSaveNationalHolidaysShouldSave() {
        //GIVEN
        List<NationalHoliday> domains = createDomains();
        List<NationalHolidayEntity> entities = createEntities();
        when(transformer.transform(domains.get(0))).thenReturn(entities.get(0));
        //WHEN
        verifyNoInteractions(repository, transformer);
        underTest.saveNationalHolidays(domains);
        //THEN
        verify(transformer).transform(domains.get(0));
        verify(repository).saveAll(entities);
        verifyNoMoreInteractions(repository, transformer);
    }

    @Test
    void testDeleteNationalHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(repository, transformer);
        underTest.deleteNationalHoliday(ID);
        //THEN
        verify(repository).deleteById(ID);
        verifyNoMoreInteractions(repository, transformer);
    }

    @Test
    void testSaveNationalHolidayShouldSave() {
        //GIVEN
        NationalHoliday domain = createDomain();
        NationalHolidayEntity entity = createEntity();
        when(transformer.transform(domain)).thenReturn(entity);
        //WHEN
        verifyNoInteractions(repository, transformer);
        underTest.saveNationalHoliday(domain);
        //THEN
        verify(transformer).transform(domain);
        verify(repository).save(entity);
        verifyNoMoreInteractions(repository, transformer);
    }

}
