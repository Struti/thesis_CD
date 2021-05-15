package com.me.thesis.holiday.dal.holidaytypes.children.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidaytypes.children.domain.ChildrenHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.children.repository.ChildrenHolidaysRepository;

/**
 * The type Children holidays dao test.
 */
class ChildrenHolidaysDaoTest {

    private static final long HOLIDAY_ID = 11111L;
    private static final String TYPE = "CHILD_HOLIDAY";
    private static final String DESCRIPTION = "days because children";
    private static final int DAYS = 20;
    private static final int CHILDREN = 3;

    @InjectMocks
    private ChildrenHolidaysDao underTest;

    @Mock
    private ChildrenHolidaysRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllChildrenShouldReturnList() {
        //GIVEN
        List<ChildrenHolidaysEntity> expected = createEntities();
        given(repository.findAll()).willReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        List<ChildrenHolidaysEntity> actual = underTest.getAllChildren();
        //THEN
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private List<ChildrenHolidaysEntity> createEntities() {
        return List.of(createEntity());
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

    @Test
    void testSaveChildrenShouldSaveChildren() {
        //GIVEN
        ChildrenHolidaysEntity holiday = createEntity();

        //WHEN
        verifyNoInteractions(repository);
        underTest.saveChildren(holiday);
        //THEN
        verify(repository, times(1)).save(holiday);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteChildrenShouldDeleteChildren() {
        //GIVEN
        //WHEN
        verifyNoInteractions(repository);
        underTest.deleteChildren(HOLIDAY_ID);
        //THEN
        verify(repository, times(1)).deleteById(HOLIDAY_ID);
        verifyNoMoreInteractions(repository);
    }

}
