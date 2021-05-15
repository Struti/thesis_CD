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

import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;
import com.me.thesis.holiday.dto.person.enums.PersonDtoGender;

/**
 * The type Person domain gender transformer test.
 */
class PersonDomainGenderTransformerTest {

    private static final PersonDtoGender PERSON_DTO_GENDER_MALE = PersonDtoGender.MALE;
    private static final PersonDomainGender PERSON_DOMAIN_GENDER_MALE = PersonDomainGender.MALE;
    private static final PersonDtoGender PERSON_DTO_GENDER_FEMALE = PersonDtoGender.FEMALE;
    private static final PersonDomainGender PERSON_DOMAIN_GENDER_FEMAIL = PersonDomainGender.FEMALE;

    @InjectMocks
    private PersonDomainGenderTransformer underTest;

    @Mock
    private Map<PersonDtoGender, PersonDomainGender> personGenderMap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformDtoToDomain() {
        //GIVEN
        when(personGenderMap.get(PERSON_DTO_GENDER_MALE)).thenReturn(PERSON_DOMAIN_GENDER_MALE);
        //WHEN
        verifyNoInteractions(personGenderMap);
        PersonDomainGender actual = underTest.transform(PERSON_DTO_GENDER_MALE);
        //THEN
        verify(personGenderMap).get(PERSON_DTO_GENDER_MALE);
        verifyNoMoreInteractions(personGenderMap);
        assertEquals(PERSON_DOMAIN_GENDER_MALE, actual);
    }

    @Test
    void testTransformShouldTransformDtoToDomainFemale() {
        //GIVEN
        when(personGenderMap.get(PERSON_DTO_GENDER_FEMALE)).thenReturn(PERSON_DOMAIN_GENDER_FEMAIL);
        //WHEN
        verifyNoInteractions(personGenderMap);
        PersonDomainGender actual = underTest.transform(PERSON_DTO_GENDER_FEMALE);
        //THEN
        verify(personGenderMap).get(PERSON_DTO_GENDER_FEMALE);
        verifyNoMoreInteractions(personGenderMap);
        assertEquals(PERSON_DOMAIN_GENDER_FEMAIL, actual);
    }
}
