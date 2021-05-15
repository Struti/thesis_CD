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

import com.me.thesis.holiday.dal.holidaytypes.maternity.dao.MaternityHolidayDao;
import com.me.thesis.holiday.dal.holidaytypes.maternity.domain.MaternityHolidayEntity;
import com.me.thesis.holiday.dal.holidaytypes.maternity.transformers.entitytransformer.MaternityHolidayEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.maternity.domain.MaternityHoliday;

/**
 * The type Maternity holidays service test.
 */
class MaternityHolidaysServiceTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "MOM_GIVE_BIRTH_HOLIDAY";
    private static final String DESCRIPTION = "days before expected date";
    private static final int DAYS = 20;

    @InjectMocks
    private MaternityHolidaysService underTest;

    @Mock
    private MaternityHolidayDao dao;

    @Mock
    private MaternityHolidayEntityTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMomGiveBirthHolidaysShouldReturnList() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        List<MaternityHolidayEntity> entities = createEntities();
        List<MaternityHoliday> expected = createDomains();
        given(dao.getAllMaternityHolidays()).willReturn(entities);
        given(transformer.transform(entities)).willReturn(expected);
        //WHEN
        verifyNoInteractions(dao, transformer);
        List<MaternityHoliday> actual = underTest.getAllMaternityHolidays();
        //THEN
        inOrder.verify(dao, times(1))
            .getAllMaternityHolidays();
        inOrder.verify(transformer, times(1))
            .transform(entities);
        verifyNoMoreInteractions(dao, transformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<MaternityHolidayEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<MaternityHoliday> createDomains() {
        return List.of(createDomain());
    }

    private MaternityHolidayEntity createEntity() {
        return MaternityHolidayEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .beforeDays(DAYS)
            .afterDays(DAYS)
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
    void testSaveMomGiveBirthHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        MaternityHolidayEntity entity = createEntity();
        MaternityHoliday domain = createDomain();
        given(transformer.transform(domain)).willReturn(entity);
        //WHEN
        verifyNoInteractions(dao, transformer);
        underTest.saveMaternityHoliday(domain);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(domain);
        inOrder.verify(dao, times(1))
            .saveMaternityHoliday(entity);
        verifyNoMoreInteractions(dao, transformer);
    }

    @Test
    void testDeleteMomGiveBirthHolidayShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(dao);
        underTest.deleteMaternityHoliday(HOLIDAY_ID);
        //THEN
        verify(dao, times(1)).deleteMaternityHoliday(HOLIDAY_ID);
        verifyNoMoreInteractions(dao);
    }

}
