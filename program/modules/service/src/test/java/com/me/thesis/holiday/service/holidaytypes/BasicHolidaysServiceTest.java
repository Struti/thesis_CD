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

import com.me.thesis.holiday.dal.holidaytypes.basic.dao.BasicHolidaysDao;
import com.me.thesis.holiday.dal.holidaytypes.basic.domain.BasicHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.basic.transformers.entitytransformer.BasicHolidaysEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;

/**
 * The type Basic holidays service test.
 */
class BasicHolidaysServiceTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "BASIC_HOLIDAY";
    private static final String DESCRIPTION = "basic holidays";
    private static final int DAYS = 20;

    @InjectMocks
    private BasicHolidaysService underTest;

    @Mock
    private BasicHolidaysDao dao;

    @Mock
    private BasicHolidaysEntityTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBasicHolidayDetailsShouldReturnList() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        List<BasicHolidaysEntity> entities = createEntities();
        List<BasicHolidays> expected = createDomains();
        given(dao.getBasicHolidays()).willReturn(entities);
        given(transformer.transform(entities)).willReturn(expected);
        //WHEN
        verifyNoInteractions(dao, transformer);
        List<BasicHolidays> actual = underTest.getAllBasicHolidayDetails();
        //THEN
        inOrder.verify(dao, times(1))
            .getBasicHolidays();
        inOrder.verify(transformer, times(1))
            .transform(entities);
        verifyNoMoreInteractions(dao, transformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<BasicHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<BasicHolidays> createDomains() {
        return List.of(createDomain());
    }

    private BasicHolidaysEntity createEntity() {
        return BasicHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    private BasicHolidays createDomain() {
        return BasicHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveBasicdHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        BasicHolidaysEntity entity = createEntity();
        BasicHolidays domain = createDomain();
        given(transformer.transform(domain)).willReturn(entity);
        //WHEN
        verifyNoInteractions(dao, transformer);
        underTest.saveBasicdHoliday(domain);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(domain);
        inOrder.verify(dao, times(1))
            .saveBasicHoliday(entity);
        verifyNoMoreInteractions(dao, transformer);
    }

    @Test
    void testDeleteBasicHolidayDetailsShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(dao);
        underTest.deleteBasicHolidayDetails(HOLIDAY_ID);
        //THEN
        verify(dao, times(1)).deleteBasicHoliday(HOLIDAY_ID);
        verifyNoMoreInteractions(dao);
    }

}
