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

import com.me.thesis.holiday.dal.person.enums.PersonEntityTitle;
import com.me.thesis.holiday.domain.person.enums.PersonDomainTitle;

/**
 * The type Person entity title transformer test.
 */
class PersonEntityTitleTransformerTest {

    private static final PersonEntityTitle PERSON_ENTITY_TITLE_NONE = PersonEntityTitle.NONE;
    private static final PersonEntityTitle PERSON_ENTITY_TITLE_DR1 = PersonEntityTitle.DR1;
    private static final PersonEntityTitle PERSON_ENTITY_TITLE_DR2 = PersonEntityTitle.DR2;
    private static final PersonEntityTitle PERSON_ENTITY_TITLE_HABIL = PersonEntityTitle.HABIL;
    private static final PersonEntityTitle PERSON_ENTITY_TITLE_PHD = PersonEntityTitle.PHD;
    private static final PersonEntityTitle PERSON_ENTITY_TITLE_PROF = PersonEntityTitle.PROF;
    private static final PersonDomainTitle PERSON_DOMAIN_TITLE_NONE = PersonDomainTitle.NONE;
    private static final PersonDomainTitle PERSON_DOMAIN_TITLE_DR1 = PersonDomainTitle.DR1;
    private static final PersonDomainTitle PERSON_DOMAIN_TITLE_DR2 = PersonDomainTitle.DR2;
    private static final PersonDomainTitle PERSON_DOMAIN_TITLE_HABIL = PersonDomainTitle.HABIL;
    private static final PersonDomainTitle PERSON_DOMAIN_TITLE_PHD = PersonDomainTitle.PHD;
    private static final PersonDomainTitle PERSON_DOMAIN_TITLE_PROF = PersonDomainTitle.PROF;

    @InjectMocks
    private PersonEntityTitleTransformer underTest;

    @Mock
    private Map<PersonEntityTitle, PersonDomainTitle> personTitleEntityToDomainMap;

    @Mock
    private Map<PersonDomainTitle, PersonEntityTitle> personTitleDomainToEntityMap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldCreateEntityNone() {
        //GIVEN
        when(personTitleDomainToEntityMap.getOrDefault(PERSON_DOMAIN_TITLE_NONE, PERSON_ENTITY_TITLE_NONE)).thenReturn(PERSON_ENTITY_TITLE_NONE);
        //WHEN
        verifyNoInteractions(personTitleDomainToEntityMap);
        PersonEntityTitle actual = underTest.transform(PERSON_DOMAIN_TITLE_NONE);
        //THEN
        verify(personTitleDomainToEntityMap, times(1)).getOrDefault(PERSON_DOMAIN_TITLE_NONE, PERSON_ENTITY_TITLE_NONE);
        verifyNoMoreInteractions(personTitleDomainToEntityMap);
        assertEquals(PERSON_ENTITY_TITLE_NONE, actual);
    }

    @Test
    void testTransformShouldPersonEntityDr1() {
        //GIVEN
        when(personTitleDomainToEntityMap.getOrDefault(PERSON_DOMAIN_TITLE_DR1, PERSON_ENTITY_TITLE_NONE)).thenReturn(PERSON_ENTITY_TITLE_DR1);
        //WHEN
        verifyNoInteractions(personTitleDomainToEntityMap);
        PersonEntityTitle actual = underTest.transform(PERSON_DOMAIN_TITLE_DR1);
        //THEN
        verify(personTitleDomainToEntityMap, times(1)).getOrDefault(PERSON_DOMAIN_TITLE_DR1, PERSON_ENTITY_TITLE_NONE);
        verifyNoMoreInteractions(personTitleDomainToEntityMap);
        assertEquals(PERSON_ENTITY_TITLE_DR1, actual);
    }

    @Test
    void testTransformShouldPersonEntityDr2() {
        //GIVEN
        when(personTitleDomainToEntityMap.getOrDefault(PERSON_DOMAIN_TITLE_DR2, PERSON_ENTITY_TITLE_NONE)).thenReturn(PERSON_ENTITY_TITLE_DR2);
        //WHEN
        verifyNoInteractions(personTitleDomainToEntityMap);
        PersonEntityTitle actual = underTest.transform(PERSON_DOMAIN_TITLE_DR2);
        //THEN
        verify(personTitleDomainToEntityMap, times(1)).getOrDefault(PERSON_DOMAIN_TITLE_DR2, PERSON_ENTITY_TITLE_NONE);
        verifyNoMoreInteractions(personTitleDomainToEntityMap);
        assertEquals(PERSON_ENTITY_TITLE_DR2, actual);
    }

    @Test
    void testTransformShouldPersonEntityHabil() {
        //GIVEN
        when(personTitleDomainToEntityMap.getOrDefault(PERSON_DOMAIN_TITLE_HABIL, PERSON_ENTITY_TITLE_NONE)).thenReturn(PERSON_ENTITY_TITLE_HABIL);
        //WHEN
        verifyNoInteractions(personTitleDomainToEntityMap);
        PersonEntityTitle actual = underTest.transform(PERSON_DOMAIN_TITLE_HABIL);
        //THEN
        verify(personTitleDomainToEntityMap, times(1)).getOrDefault(PERSON_DOMAIN_TITLE_HABIL, PERSON_ENTITY_TITLE_NONE);
        verifyNoMoreInteractions(personTitleDomainToEntityMap);
        assertEquals(PERSON_ENTITY_TITLE_HABIL, actual);
    }

    @Test
    void testTransformShouldPersonEntityPhd() {
        //GIVEN
        when(personTitleDomainToEntityMap.getOrDefault(PERSON_DOMAIN_TITLE_PHD, PERSON_ENTITY_TITLE_NONE)).thenReturn(PERSON_ENTITY_TITLE_PHD);
        //WHEN
        verifyNoInteractions(personTitleDomainToEntityMap);
        PersonEntityTitle actual = underTest.transform(PERSON_DOMAIN_TITLE_PHD);
        //THEN
        verify(personTitleDomainToEntityMap, times(1)).getOrDefault(PERSON_DOMAIN_TITLE_PHD, PERSON_ENTITY_TITLE_NONE);
        verifyNoMoreInteractions(personTitleDomainToEntityMap);
        assertEquals(PERSON_ENTITY_TITLE_PHD, actual);
    }

    @Test
    void testTransformShouldPersonEntityProf() {
        //GIVEN
        when(personTitleDomainToEntityMap.getOrDefault(PERSON_DOMAIN_TITLE_PROF, PERSON_ENTITY_TITLE_NONE)).thenReturn(PERSON_ENTITY_TITLE_PROF);
        //WHEN
        verifyNoInteractions(personTitleDomainToEntityMap);
        PersonEntityTitle actual = underTest.transform(PERSON_DOMAIN_TITLE_PROF);
        //THEN
        verify(personTitleDomainToEntityMap, times(1)).getOrDefault(PERSON_DOMAIN_TITLE_PROF, PERSON_ENTITY_TITLE_NONE);
        verifyNoMoreInteractions(personTitleDomainToEntityMap);
        assertEquals(PERSON_ENTITY_TITLE_PROF, actual);
    }

    @Test
    void testTransformShouldCreateDomainNone() {
        //GIVEN
        when(personTitleEntityToDomainMap.getOrDefault(PERSON_ENTITY_TITLE_NONE, PERSON_DOMAIN_TITLE_NONE)).thenReturn(PERSON_DOMAIN_TITLE_NONE);
        //WHEN
        verifyNoInteractions(personTitleEntityToDomainMap);
        PersonDomainTitle actual = underTest.transform(PERSON_ENTITY_TITLE_NONE);
        //THEN
        verify(personTitleEntityToDomainMap, times(1)).getOrDefault(PERSON_ENTITY_TITLE_NONE, PERSON_DOMAIN_TITLE_NONE);
        verifyNoMoreInteractions(personTitleEntityToDomainMap);
        assertEquals(PERSON_DOMAIN_TITLE_NONE, actual);
    }

    @Test
    void testTransformShouldCreateDomainDr1() {
        //GIVEN
        when(personTitleEntityToDomainMap.getOrDefault(PERSON_ENTITY_TITLE_DR1, PERSON_DOMAIN_TITLE_NONE)).thenReturn(PERSON_DOMAIN_TITLE_DR1);
        //WHEN
        verifyNoInteractions(personTitleEntityToDomainMap);
        PersonDomainTitle actual = underTest.transform(PERSON_ENTITY_TITLE_DR1);
        //THEN
        verify(personTitleEntityToDomainMap, times(1)).getOrDefault(PERSON_ENTITY_TITLE_DR1, PERSON_DOMAIN_TITLE_NONE);
        verifyNoMoreInteractions(personTitleEntityToDomainMap);
        assertEquals(PERSON_DOMAIN_TITLE_DR1, actual);
    }

    @Test
    void testTransformShouldCreateDomainDr2() {
        //GIVEN
        when(personTitleEntityToDomainMap.getOrDefault(PERSON_ENTITY_TITLE_DR2, PERSON_DOMAIN_TITLE_NONE)).thenReturn(PERSON_DOMAIN_TITLE_DR2);
        //WHEN
        verifyNoInteractions(personTitleEntityToDomainMap);
        PersonDomainTitle actual = underTest.transform(PERSON_ENTITY_TITLE_DR2);
        //THEN
        verify(personTitleEntityToDomainMap, times(1)).getOrDefault(PERSON_ENTITY_TITLE_DR2, PERSON_DOMAIN_TITLE_NONE);
        verifyNoMoreInteractions(personTitleEntityToDomainMap);
        assertEquals(PersonDomainTitle.DR2, actual);
    }

    @Test
    void testTransformShouldCreateDomainHabil() {
        //GIVEN
        when(personTitleEntityToDomainMap.getOrDefault(PERSON_ENTITY_TITLE_HABIL, PERSON_DOMAIN_TITLE_NONE)).thenReturn(PERSON_DOMAIN_TITLE_HABIL);
        //WHEN
        verifyNoInteractions(personTitleEntityToDomainMap);
        PersonDomainTitle actual = underTest.transform(PERSON_ENTITY_TITLE_HABIL);
        //THEN
        verify(personTitleEntityToDomainMap, times(1)).getOrDefault(PERSON_ENTITY_TITLE_HABIL, PERSON_DOMAIN_TITLE_NONE);
        verifyNoMoreInteractions(personTitleEntityToDomainMap);
        assertEquals(PERSON_DOMAIN_TITLE_HABIL, actual);
    }

    @Test
    void testTransformShouldCreateDomainPhd() {
        //GIVEN
        when(personTitleEntityToDomainMap.getOrDefault(PERSON_ENTITY_TITLE_PHD, PERSON_DOMAIN_TITLE_NONE)).thenReturn(PERSON_DOMAIN_TITLE_PHD);
        //WHEN
        verifyNoInteractions(personTitleEntityToDomainMap);
        PersonDomainTitle actual = underTest.transform(PERSON_ENTITY_TITLE_PHD);
        //THEN
        verify(personTitleEntityToDomainMap, times(1)).getOrDefault(PERSON_ENTITY_TITLE_PHD, PERSON_DOMAIN_TITLE_NONE);
        verifyNoMoreInteractions(personTitleEntityToDomainMap);
        assertEquals(PERSON_DOMAIN_TITLE_PHD, actual);
    }

    @Test
    void testTransformShouldCreateDomainProf() {
        //GIVEN
        when(personTitleEntityToDomainMap.getOrDefault(PERSON_ENTITY_TITLE_PROF, PERSON_DOMAIN_TITLE_NONE)).thenReturn(PERSON_DOMAIN_TITLE_PROF);
        //WHEN
        verifyNoInteractions(personTitleEntityToDomainMap);
        PersonDomainTitle actual = underTest.transform(PERSON_ENTITY_TITLE_PROF);
        //THEN
        verify(personTitleEntityToDomainMap, times(1)).getOrDefault(PERSON_ENTITY_TITLE_PROF, PERSON_DOMAIN_TITLE_NONE);
        verifyNoMoreInteractions(personTitleEntityToDomainMap);
        assertEquals(PERSON_DOMAIN_TITLE_PROF, actual);
    }
}
