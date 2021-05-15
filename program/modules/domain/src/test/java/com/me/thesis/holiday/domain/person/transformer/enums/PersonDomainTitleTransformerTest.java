package com.me.thesis.holiday.domain.person.transformer.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.me.thesis.holiday.domain.person.enums.PersonDomainTitle;
import com.me.thesis.holiday.dto.person.enums.PersonDtoTitle;

/**
 * The type Person domain title transformer test.
 */
class PersonDomainTitleTransformerTest {

    @InjectMocks
    private PersonDomainTitleTransformer underTest;

    @Mock
    private Map<PersonDtoTitle, PersonDomainTitle> personTitleMap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldCreateDomainNone() {
        //GIVEN
        when(personTitleMap.get(PersonDtoTitle.NONE)).thenReturn(PersonDomainTitle.NONE);
        //WHEN
        verifyNoInteractions(personTitleMap);
        PersonDomainTitle actual = underTest.transform(PersonDtoTitle.NONE);
        //THEN
        verify(personTitleMap).get(PersonDtoTitle.NONE);
        verifyNoMoreInteractions(personTitleMap);
        assertEquals(PersonDomainTitle.NONE, actual);
    }

    @Test
    void testTransformShouldCreateDomainDr1() {
        //GIVEN
        when(personTitleMap.get(PersonDtoTitle.DR1)).thenReturn(PersonDomainTitle.DR1);
        //WHEN
        verifyNoInteractions(personTitleMap);
        PersonDomainTitle actual = underTest.transform(PersonDtoTitle.DR1);
        //THEN
        verify(personTitleMap).get(PersonDtoTitle.DR1);
        verifyNoMoreInteractions(personTitleMap);
        assertEquals(PersonDomainTitle.DR1, actual);
    }

    @Test
    void testTransformShouldCreateDomainDr2() {
        //GIVEN
        when(personTitleMap.get(PersonDtoTitle.DR2)).thenReturn(PersonDomainTitle.DR2);
        //WHEN
        verifyNoInteractions(personTitleMap);
        PersonDomainTitle actual = underTest.transform(PersonDtoTitle.DR2);
        //THEN
        verify(personTitleMap).get(PersonDtoTitle.DR2);
        verifyNoMoreInteractions(personTitleMap);
        assertEquals(PersonDomainTitle.DR2, actual);
    }

    @Test
    void testTransformShouldCreateDomainHabil() {
        //GIVEN
        when(personTitleMap.get(PersonDtoTitle.HABIL)).thenReturn(PersonDomainTitle.HABIL);
        //WHEN
        verifyNoInteractions(personTitleMap);
        PersonDomainTitle actual = underTest.transform(PersonDtoTitle.HABIL);
        //THEN
        verify(personTitleMap).get(PersonDtoTitle.HABIL);
        verifyNoMoreInteractions(personTitleMap);
        assertEquals(PersonDomainTitle.HABIL, actual);
    }

    @Test
    void testTransformShouldCreateDomainPhd() {
        //GIVEN
        when(personTitleMap.get(PersonDtoTitle.PHD)).thenReturn(PersonDomainTitle.PHD);
        //WHEN
        verifyNoInteractions(personTitleMap);
        PersonDomainTitle actual = underTest.transform(PersonDtoTitle.PHD);
        //THEN
        verify(personTitleMap).get(PersonDtoTitle.PHD);
        verifyNoMoreInteractions(personTitleMap);
        assertEquals(PersonDomainTitle.PHD, actual);
    }

    @Test
    void testTransformShouldCreateDomainProf() {
        //GIVEN
        when(personTitleMap.get(PersonDtoTitle.PROF)).thenReturn(PersonDomainTitle.PROF);
        //WHEN
        verifyNoInteractions(personTitleMap);
        PersonDomainTitle actual = underTest.transform(PersonDtoTitle.PROF);
        //THEN
        verify(personTitleMap).get(PersonDtoTitle.PROF);
        verifyNoMoreInteractions(personTitleMap);
        assertEquals(PersonDomainTitle.PROF, actual);
    }

}
