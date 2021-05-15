package com.me.thesis.holiday.controller.holidaytypes.children;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildBorderAge;
import com.me.thesis.holiday.service.holidaytypes.ChildBorderAgeService;

/**
 * The type Child border age controller test.
 */
class ChildBorderAgeControllerTest {

    private static final int CHILD_BORDER_AGE = 16;
    private static final long ID = 1L;
    @InjectMocks
    private ChildBorderAgeController underTest;

    @Mock
    private ChildBorderAgeService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetChildBorderAgeShouldReturnWithChildBorderAge() {
        //GIVEN
        ChildBorderAge expected = createChildBorderAge();
        when(service.getChildBorderAge()).thenReturn(expected);
        //WHEN
        verifyNoInteractions(service);
        ChildBorderAge actual = underTest.getChildBorderAge();
        //THEN
        verify(service).getChildBorderAge();
        verifyNoMoreInteractions(service);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private ChildBorderAge createChildBorderAge() {
        return ChildBorderAge.builder()
            .id(ID)
            .childBorderAge(CHILD_BORDER_AGE)
            .build();
    }

    @Test
    void testSaveChildBorderAgeShouldSave() {
        //GIVEN
        ChildBorderAge childBorderAge = createChildBorderAge();
        //WHEN
        verifyNoInteractions(service);
        underTest.saveChildBorderAge(childBorderAge);
        //THEN
        verify(service).saveChildBorderAge(childBorderAge);
        verifyNoMoreInteractions(service);
    }

    @Test
    void testDeleteChildBorderAgeShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service);
        underTest.deleteChildBorderAge(ID);
        //THEN
        verify(service).deleteChildBorderAge(ID);
        verifyNoMoreInteractions(service);
    }

}
