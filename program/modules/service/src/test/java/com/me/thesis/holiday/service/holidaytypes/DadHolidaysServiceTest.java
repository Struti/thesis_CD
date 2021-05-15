package com.me.thesis.holiday.service.holidaytypes;

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

import com.me.thesis.holiday.dal.holidaytypes.dad.dao.DadHolidaysDao;
import com.me.thesis.holiday.dal.holidaytypes.dad.domain.DadHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.dad.transformers.entitytransformer.DadHolidaysEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.dad.domain.DadHolidays;

/**
 * The type Dad holidays service test.
 */
class DadHolidaysServiceTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "DAD_HOLIDAY";
    private static final String DESCRIPTION = "days because of birth, month limit";
    private static final int LIMIT = 200;
    private static final int DAYS = 20;

    @InjectMocks
    private DadHolidaysService underTest;

    @Mock
    private DadHolidaysDao dao;

    @Mock
    private DadHolidaysEntityTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDadHolidaysShouldReturnList() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        List<DadHolidaysEntity> entities = createEntities();
        List<DadHolidays> expected = createDomains();
        given(dao.getAllDadHolidays()).willReturn(entities);
        given(transformer.transform(entities)).willReturn(expected);
        //WHEN
        verifyNoInteractions(dao, transformer);
        List<DadHolidays> actual = underTest.getAllDadHolidays();
        //THEN
        inOrder.verify(dao, times(1))
            .getAllDadHolidays();
        inOrder.verify(transformer, times(1))
            .transform(entities);
        verifyNoMoreInteractions(dao, transformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<DadHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<DadHolidays> createDomains() {
        return List.of(createDomain());
    }

    private DadHolidaysEntity createEntity() {
        return DadHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .limit(LIMIT)
            .build();
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

    @Test
    void testSaveDadHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        DadHolidaysEntity entity = createEntity();
        DadHolidays domain = createDomain();
        given(transformer.transform(domain)).willReturn(entity);
        //WHEN
        verifyNoInteractions(dao, transformer);
        underTest.saveDadHoliday(domain);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(domain);
        inOrder.verify(dao, times(1))
            .saveDadHoliday(entity);
        verifyNoMoreInteractions(dao, transformer);
    }

    @Test
    void testDeleteDadHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(dao);
        underTest.deleteDadHoliday(HOLIDAY_ID);
        //THEN
        verify(dao, times(1)).deleteDadHoliday(HOLIDAY_ID);
        verifyNoMoreInteractions(dao);
    }

}
