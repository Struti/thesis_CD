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

import com.me.thesis.holiday.dal.holidaytypes.children.dao.ChildrenHolidaysDao;
import com.me.thesis.holiday.dal.holidaytypes.children.domain.ChildrenHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.children.transformer.entitytransformer.ChildrenHolidaysEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;

/**
 * The type Children holidays service test.
 */
class ChildrenHolidaysServiceTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "CHILD_HOLIDAY";
    private static final String DESCRIPTION = "days because children";
    private static final int DAYS = 20;
    private static final int CHILDREN = 3;

    @InjectMocks
    private ChildrenHolidaysService underTest;

    @Mock
    private ChildrenHolidaysDao dao;

    @Mock
    private ChildrenHolidaysEntityTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllChildrenHolidayDetailsShouldShouldReturnList() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        List<ChildrenHolidaysEntity> entities = createEntities();
        List<ChildrenHolidays> expected = createDomains();
        given(dao.getAllChildren()).willReturn(entities);
        given(transformer.transform(entities)).willReturn(expected);
        //WHEN
        verifyNoInteractions(dao, transformer);
        List<ChildrenHolidays> actual = underTest.getAllChildrenHolidayDetails();
        //THEN
        inOrder.verify(dao, times(1))
            .getAllChildren();
        inOrder.verify(transformer, times(1))
            .transform(entities);
        verifyNoMoreInteractions(dao, transformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<ChildrenHolidaysEntity> createEntities() {
        return List.of(createEntity());
    }

    private List<ChildrenHolidays> createDomains() {
        return List.of(createDomain());
    }

    private ChildrenHolidaysEntity createEntity() {
        return ChildrenHolidaysEntity.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .children(CHILDREN)
            .day(DAYS)
            .build();
    }

    private ChildrenHolidays createDomain() {
        return ChildrenHolidays.builder()
            .id(HOLIDAY_ID)
            .type(TYPE)
            .description(DESCRIPTION)
            .children(CHILDREN)
            .day(DAYS)
            .build();
    }

    @Test
    void testSaveChildrenHolidayShouldSave() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(dao, transformer);
        ChildrenHolidaysEntity entity = createEntity();
        ChildrenHolidays domain = createDomain();
        given(transformer.transform(domain)).willReturn(entity);
        //WHEN
        verifyNoInteractions(dao, transformer);
        underTest.saveChildrenHoliday(domain);
        //THEN
        inOrder.verify(transformer, times(1))
            .transform(domain);
        inOrder.verify(dao, times(1))
            .saveChildren(entity);
        verifyNoMoreInteractions(dao, transformer);
    }

    @Test
    void testDeleteAgeBasedHolidayDetailsShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(dao);
        underTest.deleteAgeBasedHolidayDetails(HOLIDAY_ID);
        //THEN
        verify(dao, times(1)).deleteChildren(HOLIDAY_ID);
        verifyNoMoreInteractions(dao);
    }

}
