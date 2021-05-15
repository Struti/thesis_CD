package com.me.thesis.holiday.dal.person.transformer.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.person.enums.PersonEntityGender;
import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;

/**
 * The type Person entity gender transformer test.
 */
class PersonEntityGenderTransformerTest {

    private static final PersonDomainGender PERSON_DOMAIN_GENDER_MALE = PersonDomainGender.MALE;
    private static final PersonDomainGender PERSON_DOMAIN_GENDER_FEMALE = PersonDomainGender.FEMALE;
    private static final PersonEntityGender PERSON_ENTITY_GENDER_MALE = PersonEntityGender.MALE;
    private static final PersonEntityGender PERSON_ENTITY_GENDER_FEMALE = PersonEntityGender.FEMALE;

    @InjectMocks
    private PersonEntityGenderTransformer underTest;

    @Mock
    private Map<PersonEntityGender, PersonDomainGender> personGenderEntityToDomainMap;

    @Mock
    private Map<PersonDomainGender, PersonEntityGender> personGenderDomainToEntityMap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformDomainToEntity() {
        //GIVEN
        when(personGenderDomainToEntityMap.getOrDefault(PERSON_DOMAIN_GENDER_MALE, PERSON_ENTITY_GENDER_MALE)).thenReturn(PERSON_ENTITY_GENDER_MALE);
        //WHEN
        verifyNoInteractions(personGenderDomainToEntityMap);
        PersonEntityGender actual = underTest.transform(PERSON_DOMAIN_GENDER_MALE);
        //THEN
        verify(personGenderDomainToEntityMap, times(1)).getOrDefault(PERSON_DOMAIN_GENDER_MALE, PERSON_ENTITY_GENDER_MALE);
        verifyNoMoreInteractions(personGenderDomainToEntityMap);
        assertEquals(PERSON_ENTITY_GENDER_MALE, actual);
    }

    @Test
    void testTransformShouldTransformDomainToEntityFemale() {
        //GIVEN
        when(personGenderDomainToEntityMap.getOrDefault(PERSON_DOMAIN_GENDER_FEMALE, PERSON_ENTITY_GENDER_MALE)).thenReturn(PERSON_ENTITY_GENDER_FEMALE);
        //WHEN
        verifyNoInteractions(personGenderDomainToEntityMap);
        PersonEntityGender actual = underTest.transform(PERSON_DOMAIN_GENDER_FEMALE);
        //THEN
        verify(personGenderDomainToEntityMap, times(1)).getOrDefault(PERSON_DOMAIN_GENDER_FEMALE, PERSON_ENTITY_GENDER_MALE);
        verifyNoMoreInteractions(personGenderDomainToEntityMap);
        assertEquals(PERSON_ENTITY_GENDER_FEMALE, actual);
    }

    @Test
    void testTransformShouldTransformEntityToDomain() {
        //GIVEN
        when(personGenderEntityToDomainMap.getOrDefault(PERSON_ENTITY_GENDER_MALE, PERSON_DOMAIN_GENDER_MALE)).thenReturn(PERSON_DOMAIN_GENDER_MALE);
        //WHEN
        verifyNoInteractions(personGenderEntityToDomainMap);
        PersonDomainGender actual = underTest.transform(PERSON_ENTITY_GENDER_MALE);
        //THEN
        verify(personGenderEntityToDomainMap, times(1)).getOrDefault(PERSON_ENTITY_GENDER_MALE, PERSON_DOMAIN_GENDER_MALE);
        verifyNoMoreInteractions(personGenderEntityToDomainMap);
        assertEquals(PERSON_DOMAIN_GENDER_MALE, actual);
    }

    @Test
    void testTransformShouldTransformEntityToDomainFemale() {
        //GIVEN
        when(personGenderEntityToDomainMap.getOrDefault(PERSON_ENTITY_GENDER_MALE, PERSON_DOMAIN_GENDER_MALE)).thenReturn(PERSON_DOMAIN_GENDER_FEMALE);
        //WHEN
        verifyNoInteractions(personGenderEntityToDomainMap);
        PersonDomainGender actual = underTest.transform(PERSON_ENTITY_GENDER_MALE);
        //THEN
        verify(personGenderEntityToDomainMap, times(1)).getOrDefault(PERSON_ENTITY_GENDER_MALE, PERSON_DOMAIN_GENDER_MALE);
        verifyNoMoreInteractions(personGenderEntityToDomainMap);
        assertEquals(PERSON_DOMAIN_GENDER_FEMALE, actual);
    }
}
