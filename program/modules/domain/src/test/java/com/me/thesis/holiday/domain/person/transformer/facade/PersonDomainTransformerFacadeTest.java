package com.me.thesis.holiday.domain.person.transformer.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.PersonSelectorDomain;
import com.me.thesis.holiday.domain.person.enums.PersonDomainEmployeeRole;
import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;
import com.me.thesis.holiday.domain.person.enums.PersonDomainTitle;
import com.me.thesis.holiday.domain.person.transformer.fulltransformer.FullPersonDomainTransformer;
import com.me.thesis.holiday.domain.person.transformer.partialtransformer.PartialPersonDomainTransformer;
import com.me.thesis.holiday.dto.person.domain.PersonDetailsDto;
import com.me.thesis.holiday.dto.person.domain.PersonSelectorDto;
import com.me.thesis.holiday.dto.person.enums.PersonDtoEmployeeRole;
import com.me.thesis.holiday.dto.person.enums.PersonDtoGender;
import com.me.thesis.holiday.dto.person.enums.PersonDtoTitle;

/**
 * The type Person domain transformer facade test.
 */
class PersonDomainTransformerFacadeTest {

    private static final long TEST_PERSON_2_ID = 809L;
    private static final long TEST_PERSON_1_ID = 9181L;
    private static final String TEST_PERSON_2_NAME = "Theódóra Ryba";
    private static final String ACTUAL_LOCATION = "Románia, Nagyvárad";
    private static final String TEST_PERSON_1_NAME = "Ananth Zapatero";
    private static final String TEST_PERSON_2_EMAIL = "Theodora.ryba@test.com";
    private static final String TEST_PERSON_1_EMAIL = "ananth.zapatero@test.com";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1973, 5, 6);
    private static final LocalDate START_DATE_AT_WORK = LocalDate.of(2014, 10, 3);

    @InjectMocks
    private PersonDomainTransformerFacade underTest;

    @Mock
    private FullPersonDomainTransformer fullPersonDomainTransformer;

    @Mock
    private PartialPersonDomainTransformer partialPersonDomainTransformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformFullDtoToFullDomainShouldCreateDomain() {
        //GIVEN
        PersonDetailsDto dto = createPersonDto(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        PersonDetails expected = createPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        given(fullPersonDomainTransformer.transform(dto)).willReturn(expected);
        //WHEN
        verifyNoInteractions(fullPersonDomainTransformer);
        PersonDetails actual = underTest.transformFullDtoToFullDomain(dto);
        //THEN
        verify(fullPersonDomainTransformer, times(1)).transform(dto);
        verifyNoMoreInteractions(fullPersonDomainTransformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private PersonDetails createPersonDetailsDomain(long id, String name) {
        return PersonDetails.builder()
            .id(id)
            .title(PersonDomainTitle.DR1)
            .fullName(name)
            .birthName(name)
            .gender(PersonDomainGender.MALE)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .child(false)
            .startDate(START_DATE_AT_WORK)
            .lastDay(LocalDate.MAX)
            .employeeRole(PersonDomainEmployeeRole.EMPLOYEE)
            .actualLocation(ACTUAL_LOCATION)
            .build();
    }

    @Test
    void testTransformShouldTransFormUserEntityListToPersonSelectorDomainSet() {
        //GIVEN
        Set<PersonSelectorDomain> domains = createPersonSelectorDomains();
        Set<PersonSelectorDto> expected = createPersonSelectorDtos();
        given(partialPersonDomainTransformer.transform(domains)).willReturn(expected);
        //WHEN
        verifyNoInteractions(partialPersonDomainTransformer);
        Set<PersonSelectorDto> actual = underTest.transformSelectorDomainSetToFullDtoSet(domains);
        //THEN
        verify(partialPersonDomainTransformer, times(1)).transform(domains);
        verifyNoMoreInteractions(partialPersonDomainTransformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private Set<PersonDetailsDto> createPersonDtosSet() {
        return Set.of(createPersonDto(TEST_PERSON_1_ID, TEST_PERSON_1_NAME),
            createPersonDto(TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private Set<PersonSelectorDto> createPersonSelectorDtos() {
        return Set.of(createPersonSelectorDto(TEST_PERSON_1_EMAIL, TEST_PERSON_1_ID, TEST_PERSON_1_NAME),
            createPersonSelectorDto(TEST_PERSON_2_EMAIL, TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private PersonSelectorDto createPersonSelectorDto(String email, long id, String name) {
        return PersonSelectorDto.builder()
            .id(id)
            .email(email)
            .name(name)
            .build();
    }

    private Set<PersonSelectorDomain> createPersonSelectorDomains() {
        return Set.of(createPersonSelectorDomain(TEST_PERSON_1_EMAIL, TEST_PERSON_1_ID, TEST_PERSON_1_NAME),
            createPersonSelectorDomain(TEST_PERSON_2_EMAIL, TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private PersonSelectorDomain createPersonSelectorDomain(String email, long id, String name) {
        return PersonSelectorDomain.builder()
            .id(id)
            .email(email)
            .name(name)
            .build();
    }

    private Set<PersonDetails> createPersonDetailsDomainsSet() {
        return Set.of(createPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME),
            createPersonDetailsDomain(TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private PersonDetailsDto createPersonDto(long id, String name) {
        return PersonDetailsDto.builder()
            .id(id)
            .title(PersonDtoTitle.DR1)
            .fullName(name)
            .birthName(name)
            .gender(PersonDtoGender.MALE)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .child(false)
            .startDate(START_DATE_AT_WORK)
            .lastDay(LocalDate.MAX)
            .employeeRole(PersonDtoEmployeeRole.EMPLOYEE)
            .actualLocation(ACTUAL_LOCATION)
            .build();
    }

}
