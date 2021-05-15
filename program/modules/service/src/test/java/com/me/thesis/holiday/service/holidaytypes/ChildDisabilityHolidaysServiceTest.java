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

import com.me.thesis.holiday.dal.holidaytypes.childdisability.dao.ChildDisabilityHolidayDao;
import com.me.thesis.holiday.dal.holidaytypes.childdisability.domain.ChildDisabilityHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.childdisability.transformer.entitytransformer.ChildDisabilityHolidaysEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;

/**
 * The type Child disability holidays service test.
 */
class ChildDisabilityHolidaysServiceTest {

    private static final int CHILDREN_NUMBER = 1;
    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "CHILD_DISABILITY_HOLIDAY";
    private static final String DESCRIPTION = "days because of 1 child disability";
    private static final int DAYS = 20;

    @InjectMocks
    private ChildDisabilityHolidaysService underTest;

    @Mock
    private ChildDisabilityHolidayDao dao;

    @Mock
    private ChildDisabilityHolidaysEntityTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllChildDisabilityHolidayDetailsShouldReturnList() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        List<ChildDisabilityHolidaysEntity> entities = createEntities();
        List<ChildDisabilityHolidays> expected = createDomains();
        given(dao.getChildrenDisabilityHolidays()).willReturn(entities);
        given(transformer.transform(entities)).willReturn(expected);
        //WHEN
        verifyNoInteractions(dao, transformer);
        List<ChildDisabilityHolidays> actual = underTest.getAllChildDisabilityHolidayDetails();
        //THEN
        inOrder.verify(dao, times(1))
            .getChildrenDisabilityHolidays();
        inOrder.verify(transformer, times(1))
            .transform(entities);
        verifyNoMoreInteractions(dao, transformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<ChildDisabilityHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<ChildDisabilityHolidays> createDomains() {
        return List.of(createDomain());
    }

    private ChildDisabilityHolidaysEntity createEntity() {
        return ChildDisabilityHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .childrenNumber(CHILDREN_NUMBER)
            .day(DAYS)
            .build();
    }

    private ChildDisabilityHolidays createDomain() {
        return ChildDisabilityHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .childrenNumber(CHILDREN_NUMBER)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveChildDisabilityHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        ChildDisabilityHolidaysEntity entity = createEntity();
        ChildDisabilityHolidays domain = createDomain();
        given(transformer.transform(domain)).willReturn(entity);
        //WHEN
        verifyNoInteractions(dao, transformer);
        underTest.saveChildDisabilityHoliday(domain);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(domain);
        inOrder.verify(dao, times(1))
            .saveChildDisabilityHoliday(entity);
        verifyNoMoreInteractions(dao, transformer);
    }

    @Test
    void testDeleteChildDisabilityHolidayDetailsShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(dao);
        underTest.deleteChildDisabilityHolidayDetails(HOLIDAY_ID);
        //THEN
        verify(dao, times(1)).deleteChildDisabilityHoliday(HOLIDAY_ID);
        verifyNoMoreInteractions(dao);
    }

}
