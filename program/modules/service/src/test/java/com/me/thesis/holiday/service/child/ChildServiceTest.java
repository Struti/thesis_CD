package com.me.thesis.holiday.service.child;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
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

import com.me.thesis.holiday.dal.child.dao.ChildDao;
import com.me.thesis.holiday.dal.child.domain.ChildEntity;
import com.me.thesis.holiday.dal.child.transformer.facade.ChildEntityTransformerFacade;
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.service.lib.CurrentDateProvider;
import com.me.thesis.holiday.service.person.PersonService;

/**
 * The type Child service test.
 */
class ChildServiceTest {

    private static final long PERSON_ID = 33333L;
    private static final Long CHILD_ID_1 = 11111L;
    private static final LocalDate BIRTH_DATE = LocalDate.of(1992, 3, 4);
    private static final Long CHILD_ID_2 = 22222L;
    private static final LocalDate EXPECTED_DATE = LocalDate.of(2021, 1, 25);
    private static final Long CHILD_ID_3 = 444444L;
    private static final LocalDate DISABILITY_CERT_EXPIRATION_DATE = LocalDate.of(2021, 1, 30);
    private static final LocalDate ENTITY_BIRTH_DATE = LocalDate.of(2021, 1, 25);
    private static final LocalDate ENTITY_EXPECTED_DATE = LocalDate.of(2021, 1, 25);
    private static final LocalDate ENTITY_DISABILITY_CERT_EXPIRATION_DATE = LocalDate.of(2021, 1, 30);
    private static final LocalDate ACTUAL_DATE = LocalDate.of(2021, 2, 28);
    private static final int EXPECTED_AGE = 29;

    @InjectMocks
    private ChildService underTest;

    @Mock
    private ChildEntityTransformerFacade transformer;

    @Mock
    private PersonService personService;

    @Mock
    private ChildDao dao;

    @Mock
    private CurrentDateProvider currentDateProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllChildrenByPersonIdShouldReturnDomainList() {
        //GIVEN
        List<ChildEntity> entities = createChildEntities();
        List<ChildDetails> expected = createChildDetailsDomain();

        given(dao.findAllChildrenByPersonId(PERSON_ID)).willReturn(entities);
        given(transformer.transformFullEntityListToDomainList(entities)).willReturn(expected);
        //WHEN
        verifyNoInteractions(transformer, dao, currentDateProvider, personService);
        List<ChildDetails> actual = underTest.getAllChildrenByPersonId(PERSON_ID);
        //THEN
        verify(dao, times(1))
            .findAllChildrenByPersonId(PERSON_ID);
        verify(transformer, times(1))
            .transformFullEntityListToDomainList(entities);
        verifyNoMoreInteractions(transformer, dao, currentDateProvider, personService);
        assertEquals(expected, actual);
    }

    private List<ChildDetails> createChildDetailsDomain() {
        PersonDetails person = createPersonDomain();
        return List.of(createBornChild(person), createExpectedChild(person), createBornChildWithDisability(person));
    }

    private PersonDetails createPersonDomain() {
        return PersonDetails.builder()
            .build();
    }

    private List<ChildEntity> createChildEntities() {
        PersonEntity person = createPersonEntity();
        return List.of(createBornChild(person), createExpectedChild(person), createBornChildWithDisability(person));
    }

    private ChildDetails createBornChild(PersonDetails person) {
        return ChildDetails.builder()
            .childId(CHILD_ID_1)
            .person(person)
            .born(true)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .build();
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
            .birthDate(ENTITY_BIRTH_DATE)
            .disability(false)
            .build();
    }

    private ChildEntity createExpectedChild(PersonEntity person) {
        return ChildEntity.builder()
            .childId(CHILD_ID_2)
            .person(person)
            .born(false)
            .expectedDate(ENTITY_EXPECTED_DATE)
            .disability(false)
            .build();
    }

    private ChildEntity createBornChildWithDisability(PersonEntity person) {
        return ChildEntity.builder()
            .childId(CHILD_ID_3)
            .person(person)
            .born(true)
            .birthDate(ENTITY_BIRTH_DATE)
            .disability(true)
            .disabilityCertExpirationDate(ENTITY_DISABILITY_CERT_EXPIRATION_DATE)
            .build();
    }

    private ChildDetails createExpectedChild(PersonDetails person) {
        return ChildDetails.builder()
            .childId(CHILD_ID_2)
            .person(person)
            .born(false)
            .expectedDate(EXPECTED_DATE)
            .disability(false)
            .build();
    }

    private ChildDetails createBornChildWithDisability(PersonDetails person) {
        return ChildDetails.builder()
            .childId(CHILD_ID_3)
            .person(person)
            .born(true)
            .birthDate(BIRTH_DATE)
            .disability(true)
            .disabilityCertExpirationDate(DISABILITY_CERT_EXPIRATION_DATE)
            .build();
    }

    @Test
    void testSaveChildShouldReturnDomain() {
        //GIVEN
        ChildEntity entity = createBornChild(createPersonEntity());
        PersonDetails personDomain = createPersonDomain();
        ChildDetails domainChild = createBornChild(personDomain);
        when(personService.getPersonById(PERSON_ID)).thenReturn(personDomain);
        when(transformer.transformChildDomainToEntity(domainChild, personDomain)).thenReturn(entity);
        //WHEN
        verifyNoInteractions(transformer, dao, currentDateProvider, personService);
        underTest.saveChild(domainChild, PERSON_ID);
        //THEN
        verify(personService).getPersonById(PERSON_ID);
        verify(transformer).transformChildDomainToEntity(domainChild, personDomain);
        verify(dao).saveChild(entity);
        verifyNoMoreInteractions(transformer, dao, currentDateProvider, personService);
    }

    @Test
    void testDeleteChildShouldReturnDomain() {
        //GIVEN
        //WHEN
        verifyNoInteractions(transformer, dao, currentDateProvider, personService);
        underTest.deleteChild(CHILD_ID_1);
        //THEN
        verify(dao).deleteChild(CHILD_ID_1);
        verifyNoMoreInteractions(transformer, dao, currentDateProvider, personService);
    }

    @Test
    void testGetChildAgeShouldReturnWithChildAge() {
        //GIVEN
        when(currentDateProvider.provide()).thenReturn(ACTUAL_DATE);
        //WHEN
        verifyNoInteractions(currentDateProvider);
        int actual = underTest.getChildAge(BIRTH_DATE);
        //THEN
        verify(currentDateProvider).provide();
        verifyNoMoreInteractions(currentDateProvider);
        assertEquals(EXPECTED_AGE, actual);
    }

}
