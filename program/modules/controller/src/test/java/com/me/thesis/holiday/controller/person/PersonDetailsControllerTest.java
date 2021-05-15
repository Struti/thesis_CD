package com.me.thesis.holiday.controller.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.PersonSelectorDomain;
import com.me.thesis.holiday.domain.person.enums.PersonDomainEmployeeRole;
import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;
import com.me.thesis.holiday.domain.person.enums.PersonDomainTitle;
import com.me.thesis.holiday.domain.person.transformer.facade.PersonDomainTransformerFacade;
import com.me.thesis.holiday.dto.person.domain.PersonDetailsDto;
import com.me.thesis.holiday.dto.person.domain.PersonSelectorDto;
import com.me.thesis.holiday.dto.person.enums.PersonDtoEmployeeRole;
import com.me.thesis.holiday.dto.person.enums.PersonDtoGender;
import com.me.thesis.holiday.dto.person.enums.PersonDtoTitle;
import com.me.thesis.holiday.service.person.PersonService;

/**
 * The type Person details controller test.
 */
class PersonDetailsControllerTest {

    private static final long TEST_PERSON_2_ID = 809L;
    private static final long TEST_PERSON_ID = 9181L;
    private static final String TEST_PERSON_2_NAME = "Theódóra Ryba";
    private static final String ACTUAL_LOCATION = "Románia, Nagyvárad";
    private static final String TEST_PERSON_NAME = "Ananth Zapatero";
    private static final String TEST_PERSON_2_EMAIL = "Theodora.ryba@test.com";
    private static final String TEST_PERSON_1_EMAIL = "ananth.zapatero@test.com";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1973, 5, 6);
    private static final LocalDate START_DATE_AT_WORK = LocalDate.of(2014, 10, 3);
    private static final long HOLIDAY_CALCULATION_PLAN_ID = 11111L;

    @InjectMocks
    private PersonDetailsController underTest;

    @Mock
    private PersonDomainTransformerFacade transformer;

    @Mock
    private PersonService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPersonSelectorsShouldReturnWithPersonSelectorDtoSet() {
        //GIVEN
        InOrder inOrder = inOrder(service, transformer);
        Set<PersonSelectorDomain> personSelectorDomains = createPersonSelectorDomains();
        Set<PersonSelectorDto> expected = createPersonSelectorDtos();

        when(service.getPersonSelectors()).thenReturn(personSelectorDomains);
        when(transformer.transformSelectorDomainSetToFullDtoSet(personSelectorDomains)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(service, transformer);
        Set<PersonSelectorDto> actual = underTest.getPersonSelectors();
        //THEN
        inOrder.verify(service)
            .getPersonSelectors();
        inOrder.verify(transformer)
            .transformSelectorDomainSetToFullDtoSet(personSelectorDomains);
        verifyNoMoreInteractions(service, transformer);
        assertEquals(expected, actual);
    }

    private PersonDetails createPersonDetailsDomain(long id, String name) {
        return PersonDetails.builder()
            .id(id)
            .title(PersonDomainTitle.NONE)
            .fullName(name)
            .birthName(name)
            .gender(PersonDomainGender.FEMALE)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .child(false)
            .startDate(START_DATE_AT_WORK)
            .lastDay(LocalDate.MAX)
            .employeeRole(PersonDomainEmployeeRole.SUPERVISOR)
            .actualLocation(ACTUAL_LOCATION)
            .build();
    }

    private Set<PersonSelectorDomain> createPersonSelectorDomains() {
        return Set.of(createPersonSelectorDomain(TEST_PERSON_1_EMAIL, TEST_PERSON_ID, TEST_PERSON_NAME),
            createPersonSelectorDomain(TEST_PERSON_2_EMAIL, TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private Set<PersonSelectorDto> createPersonSelectorDtos() {
        return Set.of(createPersonSelectorDto(TEST_PERSON_1_EMAIL, TEST_PERSON_ID, TEST_PERSON_NAME),
            createPersonSelectorDto(TEST_PERSON_2_EMAIL, TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    @Test
    void testGetPersonDetailsShouldReturnWithPersonDetailsDto() {
        //GIVEN
        PersonDetails expected = createPersonDetailsDomain(TEST_PERSON_ID, TEST_PERSON_NAME);

        when(service.getPersonById(TEST_PERSON_ID)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(service);
        PersonDetails actual = underTest.getPersonDetails(TEST_PERSON_ID);
        //THEN
        assertEquals(expected, actual);
        verify(service).getPersonById(TEST_PERSON_ID);
        verifyNoMoreInteractions(service);
    }

    @Test
    void testSaveUserShouldSavePerson() {
        //GIVEN
        InOrder inOrder = inOrder(transformer, service);
        PersonDetailsDto dto = createPersonDetailsDto();
        PersonDetails domain = createPersonDetailsDomain(TEST_PERSON_ID, TEST_PERSON_NAME);
        given(transformer.transformFullDtoToFullDomain(dto)).willReturn(domain);
        //WHEN
        verifyNoInteractions(transformer, service);
        underTest.savePerson(dto);
        //THEN
        inOrder.verify(transformer)
            .transformFullDtoToFullDomain(dto);
        inOrder.verify(service)
            .savePerson(domain, dto.getHolidayCalculationPlan()
                .orElse(null));
        verifyNoMoreInteractions(transformer, service);
    }

    private PersonDetailsDto createPersonDetailsDto() {
        return PersonDetailsDto.builder()
            .id(TEST_PERSON_ID)
            .title(PersonDtoTitle.NONE)
            .fullName(TEST_PERSON_NAME)
            .birthName(TEST_PERSON_NAME)
            .gender(PersonDtoGender.FEMALE)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .child(false)
            .startDate(START_DATE_AT_WORK)
            .lastDay(LocalDate.MAX)
            .employeeRole(PersonDtoEmployeeRole.SUPERVISOR)
            .actualLocation(ACTUAL_LOCATION)
            .holidayCalculationPlan(HOLIDAY_CALCULATION_PLAN_ID)
            .build();
    }

    @Test
    void testDeleteUserShouldDeletePerson() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service);
        underTest.deletePerson(TEST_PERSON_ID);
        //THEN
        verify(service).deletePerson(TEST_PERSON_ID);
        verifyNoMoreInteractions(service);
    }

    @Test
    void testGetAllPersonDetailsShouldCallServiceAndTransformerInOrderAndReturnDetailsSetDto() {
        //GIVEN
        Set<PersonDetails> expected = createPersonDetailsDomains();

        when(service.getAllPersonDetails()).thenReturn(expected);
        //WHEN
        verifyNoInteractions(service);
        Set<PersonDetails> actual = underTest.getAllPersonDetails();
        //THEN
        verify(service).getAllPersonDetails();
        verifyNoMoreInteractions(service);
        assertEquals(expected, actual);
    }

    private PersonSelectorDto createPersonSelectorDto(String email, long id, String name) {
        return PersonSelectorDto.builder()
            .id(id)
            .name(name)
            .email(email)
            .build();
    }

    private Set<PersonDetails> createPersonDetailsDomains() {
        return Set.of(createPersonDetailsDomain(TEST_PERSON_ID, TEST_PERSON_NAME),
            createPersonDetailsDomain(TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private PersonSelectorDomain createPersonSelectorDomain(String email, long id, String name) {
        return PersonSelectorDomain.builder()
            .id(id)
            .name(name)
            .email(email)
            .build();
    }

    @Test
    void testSpecifyHolidayPlanShouldCallServiceAnd() {
        //GIVEN
        //WHEN
        verifyNoInteractions(service);
        underTest.specifyHolidayPlan(TEST_PERSON_ID);
        //THEN
        verify(service).specifyPlan(TEST_PERSON_ID);
        verifyNoMoreInteractions(service);
    }

}
