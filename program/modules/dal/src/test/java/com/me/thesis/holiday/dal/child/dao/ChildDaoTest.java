package com.me.thesis.holiday.dal.child.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.child.domain.ChildEntity;
import com.me.thesis.holiday.dal.child.repository.ChildRepository;
import com.me.thesis.holiday.dal.person.domain.PersonEntity;

/**
 * The type Child dao test.
 */
class ChildDaoTest {

    private static final long PERSON_ID = 33333L;
    private static final long CHILD_ID_1 = 11111L;
    private static final long CHILD_ID_2 = 22222L;
    private static final long CHILD_ID_3 = 444444L;
    private static final LocalDate BIRTH_DATE = LocalDate.of(2021, 1, 25);
    private static final LocalDate EXPECTED_DATE = LocalDate.of(2021, 1, 25);
    private static final LocalDate DISABILITY_CERT_EXPIRATION_DATE = LocalDate.of(2021, 1, 30);

    @InjectMocks
    private ChildDao underTest;

    @Mock
    private ChildRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllChildrenByPersonIdShouldReturnEntityList() {
        //GIVEN
        List<ChildEntity> expected = createChildEntities();
        given(repository.findAllByPerson_PersonId(PERSON_ID)).willReturn(expected);
        //WHEN
        verifyNoInteractions(repository);
        List<ChildEntity> actual = underTest.findAllChildrenByPersonId(PERSON_ID);
        //THEN
        verify(repository).findAllByPerson_PersonId(PERSON_ID);
        verifyNoMoreInteractions(repository);
        assertEquals(expected, actual);
    }

    @Test
    void testSaveChildShouldReturnChildEntityWhenEntitySaved() {
        //GIVEN
        ChildEntity expected = createBornChild(createPersonEntity());
        //WHEN
        verifyNoInteractions(repository);
        underTest.saveChild(expected);
        //THEN
        verify(repository).save(expected);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteChildShouldReturnChildEntityAfterDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(repository);
        underTest.deleteChild(CHILD_ID_1);
        //THEN
        verify(repository).deleteById(CHILD_ID_1);
        verifyNoMoreInteractions(repository);
    }

    private List<ChildEntity> createChildEntities() {
        PersonEntity person = createPersonEntity();
        return List.of(createBornChild(person), createExpectedChild(person), createBornChildWithDisability(person));
    }

    private PersonEntity createPersonEntity() {
        return PersonEntity.builder()
            .build();
    }

    private ChildEntity createBornChild(PersonEntity person) {
        return ChildEntity.builder()
            .childId(CHILD_ID_1)
            .person(person)
            .born(true)
            .expectedDate(null)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .disabilityCertExpirationDate(null)
            .build();
    }

    private ChildEntity createExpectedChild(PersonEntity person) {
        return ChildEntity.builder()
            .childId(CHILD_ID_2)
            .person(person)
            .born(false)
            .expectedDate(EXPECTED_DATE)
            .birthDate(null)
            .disability(false)
            .build();
    }

    private ChildEntity createBornChildWithDisability(PersonEntity person) {
        return ChildEntity.builder()
            .childId(CHILD_ID_3)
            .person(person)
            .born(true)
            .expectedDate(null)
            .birthDate(BIRTH_DATE)
            .disability(true)
            .disabilityCertExpirationDate(DISABILITY_CERT_EXPIRATION_DATE)
            .build();
    }

}
