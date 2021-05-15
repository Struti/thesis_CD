package com.me.thesis.holiday.dal.child.transformer.fulltransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
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
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.transformer.fulltransformer.FullPersonEntityTransformer;
import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;

/**
 * The type Child full entity transformer test.
 */
class ChildFullEntityTransformerTest {

    private static final Long CHILD_ID_1 = 11111L;
    private static final LocalDate BIRTH_DATE = LocalDate.of(2021, 1, 25);
    private static final Long CHILD_ID_2 = 22222L;
    private static final LocalDate EXPECTED_DATE = LocalDate.of(2021, 1, 25);
    private static final Long CHILD_ID_3 = 444444L;
    private static final LocalDate DISABILITY_CERT_EXPIRATION_DATE = LocalDate.of(2021, 1, 30);
    private static final LocalDate ENTITY_BIRTH_DATE = LocalDate.of(2021, 1, 25);
    private static final LocalDate ENTITY_EXPECTED_DATE = LocalDate.of(2021, 1, 25);
    private static final LocalDate ENTITY_DISABILITY_CERT_EXPIRATION_DATE = LocalDate.of(2021, 1, 30);
    private static final LocalDate DATE_OPTIONAL = null;

    @InjectMocks
    private ChildFullEntityTransformer underTest;

    @Mock
    private FullPersonEntityTransformer fullPersonTransformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformList() {
        //GIVEN
        List<ChildEntity> entities = createChildEntities();
        List<ChildDetails> expected = createChildDetailsDomain();
        PersonDetails person = createPersonDomain();
        PersonEntity personEntity = createPersonEntity();

        given(fullPersonTransformer.transform(personEntity)).willReturn(person);
        //WHEN
        verifyNoInteractions(fullPersonTransformer);
        List<ChildDetails> actual = underTest.transform(entities);
        //THEN
        verify(fullPersonTransformer, times(3)).transform(personEntity);
        verifyNoMoreInteractions(fullPersonTransformer);
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
            .expectedDate(DATE_OPTIONAL)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .disabilityCertExpirationDate(DATE_OPTIONAL)
            .build();
    }

    private ChildDetails createExpectedChild(PersonDetails person) {
        return ChildDetails.builder()
            .childId(CHILD_ID_2)
            .person(person)
            .born(false)
            .expectedDate(EXPECTED_DATE)
            .birthDate(DATE_OPTIONAL)
            .disability(false)
            .disabilityCertExpirationDate(DATE_OPTIONAL)
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

    private ChildDetails createBornChildWithDisability(PersonDetails person) {
        return ChildDetails.builder()
            .childId(CHILD_ID_3)
            .person(person)
            .born(true)
            .expectedDate(DATE_OPTIONAL)
            .birthDate(BIRTH_DATE)
            .disability(true)
            .disabilityCertExpirationDate(DISABILITY_CERT_EXPIRATION_DATE)
            .build();
    }

    @Test
    void testTransformShouldTransformEntity() {
        //GIVEN
        ChildEntity entity = createBornChild(createPersonEntity());
        ChildDetails expected = createBornChild(createPersonDomain());
        PersonDetails person = createPersonDomain();
        PersonEntity personEntity = createPersonEntity();

        given(fullPersonTransformer.transform(personEntity)).willReturn(person);
        //WHEN
        verifyNoInteractions(fullPersonTransformer);
        ChildDetails actual = underTest.transform(entity);
        //THEN
        verify(fullPersonTransformer).transform(personEntity);
        verifyNoMoreInteractions(fullPersonTransformer);
        assertEquals(expected, actual);
    }

    @Test
    void testTransformShouldTransformDomainWithToInput() {
        //GIVEN
        ChildDetails domain = createBornChild(createPersonDomain());
        ChildEntity expected = createBornChild(createPersonEntity());
        PersonDetails personDomain = createPersonDomain();
        PersonEntity personEntity = createPersonEntity();

        given(fullPersonTransformer.transform(personDomain)).willReturn(personEntity);
        //WHEN
        verifyNoInteractions(fullPersonTransformer);
        ChildEntity actual = underTest.transform(domain, personDomain);
        //THEN
        verify(fullPersonTransformer).transform(personDomain);
        verifyNoMoreInteractions(fullPersonTransformer);
        assertEquals(expected, actual);
    }

}
