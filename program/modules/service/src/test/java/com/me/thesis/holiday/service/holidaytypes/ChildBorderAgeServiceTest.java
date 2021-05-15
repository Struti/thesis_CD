package com.me.thesis.holiday.service.holidaytypes;

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

import com.me.thesis.holiday.dal.holidaytypes.children.dao.ChildBorderAgeDao;
import com.me.thesis.holiday.dal.holidaytypes.children.domain.ChildBorderAgeEntity;
import com.me.thesis.holiday.dal.holidaytypes.children.transformer.entitytransformer.ChildBorderAgeEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildBorderAge;

/**
 * The type Child border age service test.
 */
class ChildBorderAgeServiceTest {

    private static final int BORDER_AGE = 16;
    private static final long CHILD_AGE_BORDER_ID = 1111L;

    @InjectMocks
    private ChildBorderAgeService underTest;

    @Mock
    private ChildBorderAgeDao dao;

    @Mock
    private ChildBorderAgeEntityTransformer transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAgeShouldReturnAge() {
        //GIVEN
        when(dao.getChildBorderAge()).thenReturn(BORDER_AGE);
        //WHEN
        Integer actual = underTest.getAge();
        //THEN
        assertEquals(BORDER_AGE, actual);
    }

    @Test
    void testGetChildBorderAgeShouldReturnAgeObject() {
        //GIVEN
        ChildBorderAge expected = createBorderAge();
        ChildBorderAgeEntity borderAgeEntity = createBorderAgeEntity();
        when(dao.getChildBorderAgeEntity()).thenReturn(borderAgeEntity);
        when(transformer.transform(borderAgeEntity)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(dao, transformer);
        ChildBorderAge actual = underTest.getChildBorderAge();
        //THEN
        verify(dao).getChildBorderAgeEntity();
        verify(transformer).transform(borderAgeEntity);
        verifyNoMoreInteractions(dao, transformer);
        assertEquals(expected, actual);
    }

    private ChildBorderAge createBorderAge() {
        return ChildBorderAge.builder()
            .id(CHILD_AGE_BORDER_ID)
            .childBorderAge(BORDER_AGE)
            .build();
    }

    private ChildBorderAgeEntity createBorderAgeEntity() {
        return ChildBorderAgeEntity.builder()
            .borderAge(BORDER_AGE)
            .childAgeBorderId(CHILD_AGE_BORDER_ID)
            .build();
    }

    @Test
    void testSaveChildBorderAgeShouldSave() {
        //GIVEN
        ChildBorderAge borderAge = createBorderAge();
        ChildBorderAgeEntity ageEntity = createBorderAgeEntity();
        when(transformer.transform(borderAge)).thenReturn(ageEntity);
        //WHEN
        verifyNoInteractions(dao, transformer);
        underTest.saveChildBorderAge(borderAge);
        //THEN
        verify(transformer).transform(borderAge);
        verify(dao).saveChildBorderAge(ageEntity);
        verifyNoMoreInteractions(dao, transformer);
    }

    @Test
    void testDeleteChildBorderAgeShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(dao, transformer);
        underTest.deleteChildBorderAge(CHILD_AGE_BORDER_ID);
        //THEN
        verify(dao).deleteChildBorderAge(CHILD_AGE_BORDER_ID);
        verifyNoMoreInteractions(dao, transformer);
    }

}
