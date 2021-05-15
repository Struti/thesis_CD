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

import com.me.thesis.holiday.dal.holidaytypes.death.dao.DeathHolidaysDao;
import com.me.thesis.holiday.dal.holidaytypes.death.domain.DeathHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.death.transformers.entitytransformer.DeathHolidaysEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.death.domain.DeathHolidays;

/**
 * The type Death holidays service test.
 */
class DeathHolidaysServiceTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "DEATH_HOLIDAY";
    private static final String DESCRIPTION = "death cause holidays";
    private static final int DAYS = 20;

    @InjectMocks
    private DeathHolidaysService underTest;

    @Mock
    private DeathHolidaysDao dao;

    @Mock
    private DeathHolidaysEntityTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDeathHolidayDetailsShouldReturnList() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        List<DeathHolidaysEntity> entities = createEntities();
        List<DeathHolidays> expected = createDomains();
        given(dao.getDeathHolidays()).willReturn(entities);
        given(transformer.transform(entities)).willReturn(expected);
        //WHEN
        verifyNoInteractions(dao, transformer);
        List<DeathHolidays> actual = underTest.getAllDeathHolidayDetails();
        //THEN
        inOrder.verify(dao, times(1))
            .getDeathHolidays();
        inOrder.verify(transformer, times(1))
            .transform(entities);
        verifyNoMoreInteractions(dao, transformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<DeathHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<DeathHolidays> createDomains() {
        return List.of(createDomain());
    }

    private DeathHolidaysEntity createEntity() {
        return DeathHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    private DeathHolidays createDomain() {
        return DeathHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveDeathHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        DeathHolidaysEntity entity = createEntity();
        DeathHolidays domain = createDomain();
        given(transformer.transform(domain)).willReturn(entity);
        //WHEN
        verifyNoInteractions(dao, transformer);
        underTest.saveDeathHoliday(domain);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(domain);
        inOrder.verify(dao, times(1))
            .saveDeathHoliday(entity);
        verifyNoMoreInteractions(dao, transformer);
    }

    @Test
    void testDeleteDeathHolidayDetailsShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(dao);
        underTest.deleteDeathHolidayDetails(HOLIDAY_ID);
        //THEN
        verify(dao, times(1)).deleteDeathHoliday(HOLIDAY_ID);
        verifyNoMoreInteractions(dao);
    }

}
