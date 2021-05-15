package com.me.thesis.holiday.dal.holidaytypes.children.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidaytypes.children.domain.ChildBorderAgeEntity;
import com.me.thesis.holiday.dal.holidaytypes.children.repository.ChildBorderAgeRepository;

/**
 * The type Child border age dao test.
 */
class ChildBorderAgeDaoTest {

    private static final long CHILD_AGE_BORDER_ID = 1111111L;
    private static final int BORDER_AGE = 16;

    @InjectMocks
    private ChildBorderAgeDao underTest;

    @Mock
    private ChildBorderAgeRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveChildBorderAgeShouldSave() {
        //GIVEN
        ChildBorderAgeEntity ageEntity = createChildBorderAgeEntity();
        //WHEN
        verifyNoInteractions(repository);
        underTest.saveChildBorderAge(ageEntity);
        //THEN
        verify(repository).save(ageEntity);
        verifyNoMoreInteractions(repository);
    }

    private ChildBorderAgeEntity createChildBorderAgeEntity() {
        return ChildBorderAgeEntity.builder()
            .childAgeBorderId(CHILD_AGE_BORDER_ID)
            .borderAge(BORDER_AGE)
            .build();
    }

    @Test
    void testDeleteChildBorderAgeShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(repository);
        underTest.deleteChildBorderAge(CHILD_AGE_BORDER_ID);
        //THEN
        verify(repository).deleteById(CHILD_AGE_BORDER_ID);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testGetChildBorderAgesShouldReturnIntegerList() {
        //GIVEN
        when(repository.findAll()).thenReturn(List.of(createChildBorderAgeEntity()));
        //WHEN
        verifyNoInteractions(repository);
        Integer actual = underTest.getChildBorderAge();
        //THEN
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(BORDER_AGE, actual);
    }

    @Test
    void testGetChildBorderAgeShouldReturnWithEntity() {
        //GIVEN
        ChildBorderAgeEntity expected = createChildBorderAgeEntity();
        List<ChildBorderAgeEntity> entities = List.of(expected);
        when(repository.findAll()).thenReturn(entities);
        //WHEN
        verifyNoInteractions(repository);
        ChildBorderAgeEntity actual = underTest.getChildBorderAgeEntity();
        //THEN
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
    }

}
