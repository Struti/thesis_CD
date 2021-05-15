package com.me.thesis.holiday.service.holidayplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;
import com.me.thesis.holiday.service.child.ChildService;
import com.me.thesis.holiday.service.holidaytypes.ChildBorderAgeService;
import com.me.thesis.holiday.service.holidaytypes.ChildDisabilityHolidaysService;
import com.me.thesis.holiday.service.holidaytypes.ChildrenHolidaysService;

/**
 * The type Child related plan fragment provider test.
 */
class ChildRelatedPlanFragmentProviderTest {

    private static final long CHILD_ID = 1111L;
    private static final LocalDate BIRTH_DATE_OVER_BORDER_AGE = LocalDate.of(1992, 3, 4);
    private static final int OVER_AGE = 29;
    private static final Integer CHILD_BORDER_AGE = 16;
    private static final String DESCRIPTION = "desc";
    private static final String TYPE = "type";
    private static final int CHILDREN_COUNT = 1;
    private static final int DAY = 20;
    private static final LocalDate BIRTH_DATE = LocalDate.of(2020, 1, 2);
    private static final Integer AGE = 1;

    @InjectMocks
    private ChildRelatedPlanFragmentProvider underTest;

    @Mock
    private ChildrenHolidaysService childrenHolidaysService;

    @Mock
    private ChildBorderAgeService childBorderAgeService;

    @Mock
    private ChildService childService;

    @Mock
    private ChildDisabilityHolidaysService childDisabilityHolidaysService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetChildrenHolidayShouldReturnNull() {
        //GIVEN
        List<ChildrenHolidays> childrenHolidays = createChildrenHolidays();
        List<ChildDetails> childrenOverBorderAge = createChildrenOverBorderAge();
        when(childrenHolidaysService.getAllChildrenHolidayDetails()).thenReturn(childrenHolidays);
        when(childBorderAgeService.getAge()).thenReturn(CHILD_BORDER_AGE);
        when(childService.getChildAge(BIRTH_DATE_OVER_BORDER_AGE)).thenReturn(OVER_AGE);
        //WHEN
        verifyNoInteractions(childrenHolidaysService, childBorderAgeService, childService, childDisabilityHolidaysService);
        ChildrenHolidays actual = underTest.getChildrenHoliday(childrenOverBorderAge);
        //THEN
        verify(childrenHolidaysService).getAllChildrenHolidayDetails();
        verify(childBorderAgeService).getAge();
        verify(childService).getChildAge(BIRTH_DATE_OVER_BORDER_AGE);
        verifyNoMoreInteractions(childrenHolidaysService, childBorderAgeService, childService, childDisabilityHolidaysService);
        assertNull(actual);
    }

    private List<ChildrenHolidays> createChildrenHolidays() {
        return List.of(createChildrenHoliday());
    }

    private List<ChildDetails> createChildrenOverBorderAge() {
        return List.of(createChild(true, BIRTH_DATE_OVER_BORDER_AGE));
    }

    private ChildrenHolidays createChildrenHoliday() {
        return ChildrenHolidays.builder()
            .id(CHILD_ID)
            .description(DESCRIPTION)
            .type(TYPE)
            .children(CHILDREN_COUNT)
            .day(DAY)
            .build();
    }

    private ChildDetails createChild(boolean isDisabled, LocalDate birthDate) {
        return ChildDetails.builder()
            .childId(CHILD_ID)
            .born(true)
            .disability(isDisabled)
            .birthDate(birthDate)
            .build();
    }

    @Test
    void testGetChildrenHolidayShouldReturnOneChildObject() {
        //GIVEN
        List<ChildrenHolidays> childrenHolidays = createChildrenHolidays();
        List<ChildDetails> children = crateChildren();
        ChildrenHolidays expected = createChildrenHoliday();
        when(childrenHolidaysService.getAllChildrenHolidayDetails()).thenReturn(childrenHolidays);
        when(childBorderAgeService.getAge()).thenReturn(CHILD_BORDER_AGE);
        when(childService.getChildAge(BIRTH_DATE)).thenReturn(AGE);
        //WHEN
        verifyNoInteractions(childrenHolidaysService, childBorderAgeService, childService, childDisabilityHolidaysService);
        ChildrenHolidays actual = underTest.getChildrenHoliday(children);
        //THEN
        verify(childrenHolidaysService).getAllChildrenHolidayDetails();
        verify(childBorderAgeService).getAge();
        verify(childService).getChildAge(BIRTH_DATE);
        verifyNoMoreInteractions(childrenHolidaysService, childBorderAgeService, childService, childDisabilityHolidaysService);
        assertEquals(expected, actual);
    }

    private List<ChildDetails> crateChildren() {
        return List.of(createChild(false, BIRTH_DATE));
    }

    @Test
    void testGetChildDisabilityHolidayShouldReturnNull() {
        //GIVEN
        List<ChildDisabilityHolidays> childDisabilityHolidays = createChildDisabilityHolidays();
        List<ChildDetails> child = crateChildren();
        when(childDisabilityHolidaysService.getAllChildDisabilityHolidayDetails()).thenReturn(childDisabilityHolidays);
        //WHEN
        verifyNoInteractions(childrenHolidaysService, childBorderAgeService, childService, childDisabilityHolidaysService);
        ChildDisabilityHolidays actual = underTest.getChildDisabilityHoliday(child);
        //THEN
        verify(childDisabilityHolidaysService).getAllChildDisabilityHolidayDetails();
        verifyNoMoreInteractions(childrenHolidaysService, childBorderAgeService, childService, childDisabilityHolidaysService);
        assertNull(actual);
    }

    private List<ChildDisabilityHolidays> createChildDisabilityHolidays() {
        return List.of(createChildDisabilityHoliday());
    }

    private ChildDisabilityHolidays createChildDisabilityHoliday() {
        return ChildDisabilityHolidays.builder()
            .id(CHILD_ID)
            .description(DESCRIPTION)
            .day(DAY)
            .type(TYPE)
            .build();
    }

    @Test
    void testGetChildDisabilityHolidayShouldReturnOneChildObject() {
        //GIVEN
        List<ChildDisabilityHolidays> childDisabilityHolidays = createChildDisabilityHolidays();
        List<ChildDetails> child = createChildrenOverBorderAge();
        ChildDisabilityHolidays expected = createChildDisabilityHoliday();
        when(childDisabilityHolidaysService.getAllChildDisabilityHolidayDetails()).thenReturn(childDisabilityHolidays);
        //WHEN
        verifyNoInteractions(childrenHolidaysService, childBorderAgeService, childService, childDisabilityHolidaysService);
        ChildDisabilityHolidays actual = underTest.getChildDisabilityHoliday(child);
        //THEN
        verify(childDisabilityHolidaysService).getAllChildDisabilityHolidayDetails();
        verifyNoMoreInteractions(childrenHolidaysService, childBorderAgeService, childService, childDisabilityHolidaysService);
        assertEquals(expected, actual);
    }

}
