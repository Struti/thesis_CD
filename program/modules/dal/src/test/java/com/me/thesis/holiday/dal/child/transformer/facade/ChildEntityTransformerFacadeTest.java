package com.me.thesis.holiday.dal.child.transformer.facade;

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
import com.me.thesis.holiday.dal.child.transformer.fulltransformer.ChildFullEntityTransformer;
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;

/**
 * The type Child entity transformer facade test.
 */
class ChildEntityTransformerFacadeTest {

    private static final Long CHILD_ID_1 = 11111L;
    private static final LocalDate BIRTH_DATE = LocalDate.of(2021, 1, 25);
    private static final Long CHILD_ID_2 = 22222L;
    private static final LocalDate EXPECTED_DATE = LocalDate.of(2021, 1, 25);
    private static final Long CHILD_ID_3 = 444444L;
    private static final LocalDate DISABILITY_CERT_EXPIRATION_DATE = LocalDate.of(2021, 1, 30);
    private static final LocalDate ENTITY_BIRTH_DATE = LocalDate.of(2021, 1, 25);
    private static final LocalDate ENTITY_EXPECTED_DATE = LocalDate.of(2021, 1, 25);
    private static final LocalDate ENTITY_DISABILITY_CERT_EXPIRATION_DATE = LocalDate.of(2021, 1, 30);

    @InjectMocks
    private ChildEntityTransformerFacade underTest;

    @Mock
    private ChildFullEntityTransformer fullTransformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformFullEntityListToDomainListShouldTransform() {
        //GIVEN
        List<ChildEntity> entities = createChildEntities();
        List<ChildDetails> expected = createChildDetailsDomain();

        given(fullTransformer.transform(entities)).willReturn(expected);
        //WHEN
        verifyNoInteractions(fullTransformer);
        List<ChildDetails> actual = underTest.transformFullEntityListToDomainList(entities);
        //THEN
        verify(fullTransformer).transform(entities);
        verifyNoMoreInteractions(fullTransformer);
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
    void testTransformChildDomainToEntityShouldTransform() {
        //GIVEN
        PersonDetails personDomain = createPersonDomain();
        ChildDetails domain = createBornChild(personDomain);
        ChildEntity expected = createBornChild(createPersonEntity());

        given(fullTransformer.transform(domain, personDomain)).willReturn(expected);
        //WHEN
        verifyNoInteractions(fullTransformer);
        ChildEntity actual = underTest.transformChildDomainToEntity(domain, personDomain);
        //THEN
        verify(fullTransformer).transform(domain, personDomain);
        verifyNoMoreInteractions(fullTransformer);
        assertEquals(expected, actual);
    }

    @Test
    void testTransformChildEntityToDomainShouldTransform() {
        //GIVEN
        ChildEntity entity = createBornChild(createPersonEntity());
        ChildDetails expected = createBornChild(createPersonDomain());

        given(fullTransformer.transform(entity)).willReturn(expected);
        //WHEN
        verifyNoInteractions(fullTransformer);
        ChildDetails actual = underTest.transformChildEntityToDomain(entity);
        //THEN
        verify(fullTransformer).transform(entity);
        verifyNoMoreInteractions(fullTransformer);
        assertEquals(expected, actual);
    }

}
