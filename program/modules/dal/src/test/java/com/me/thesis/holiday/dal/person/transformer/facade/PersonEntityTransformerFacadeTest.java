package com.me.thesis.holiday.dal.person.transformer.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidayplan.domain.HolidayCalculationPlanEntity;
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.enums.PersonEntityEmployeeRole;
import com.me.thesis.holiday.dal.person.enums.PersonEntityGender;
import com.me.thesis.holiday.dal.person.enums.PersonEntityTitle;
import com.me.thesis.holiday.dal.person.transformer.fulltransformer.FullPersonEntityTransformer;
import com.me.thesis.holiday.dal.person.transformer.partialtransformer.PartialPersonEntityTransformer;
import com.me.thesis.holiday.dal.user.domain.UserEntity;
import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.holidayplan.domain.Plan;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.PersonSelectorDomain;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;
import com.me.thesis.holiday.domain.person.enums.PersonDomainEmployeeRole;
import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;
import com.me.thesis.holiday.domain.person.enums.PersonDomainTitle;

/**
 * The type Person entity transformer facade test.
 */
class PersonEntityTransformerFacadeTest {

    private static final long TEST_PERSON_2_ID = 809L;
    private static final long TEST_PERSON_1_ID = 9181L;
    private static final String TEST_PERSON_2_NAME = "Theódóra Ryba";
    private static final String ACTUAL_LOCATION = "Románia, Nagyvárad";
    private static final String TEST_PERSON_1_NAME = "Ananth Zapatero";
    private static final String TEST_PERSON_2_EMAIL = "Theodora.ryba@test.com";
    private static final String TEST_PERSON_1_EMAIL = "ananth.zapatero@test.com";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1973, 5, 6);
    private static final LocalDate START_DATE_AT_WORK = LocalDate.of(2014, 10, 3);
    private static final long CALCULATION_PLAN_ID = 111111L;
    private static final String DESCRIPTION = "desc";
    private static final String PLAN = "plan";
    private static final String SPECIFIC_PLAN = "SpecPlan";

    @InjectMocks
    private PersonEntityTransformerFacade underTest;

    @Mock
    private PartialPersonEntityTransformer partialPersonEntityTransformer;

    @Mock
    private FullPersonEntityTransformer fullPersonEntityTransformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformFullEntityDetailsToDomain() {
        //GIVEN
        PersonEntity entity = createPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        PersonDetails expected = createPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        given(fullPersonEntityTransformer.transform(entity)).willReturn(expected);
        //WHEN
        verifyNoInteractions(fullPersonEntityTransformer);
        PersonDetails actual = underTest.transformFullEntityDetailsToDomain(entity);
        //THEN
        verify(fullPersonEntityTransformer).transform(entity);
        verifyNoMoreInteractions(fullPersonEntityTransformer);
        assertEquals(expected, actual);
    }

    private PersonEntity createPersonEntity(long id, String name) {
        return PersonEntity.builder()
            .personId(id)
            .title(PersonEntityTitle.DR1)
            .fullName(name)
            .birthName(name)
            .gender(PersonEntityGender.MALE)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .child(false)
            .startDate(START_DATE_AT_WORK)
            .lastDay(LocalDate.MAX)
            .employeeRole(PersonEntityEmployeeRole.EMPLOYEE)
            .actualLocation(ACTUAL_LOCATION)
            .holidayCalculationPlanEntity(createHolidayCalculationPlanEntity())
            .specificPlan(SPECIFIC_PLAN)
            .build();
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
            .calculationPlan(createHolidayCalculationPlan())
            .specificPlan(createSpecificPlan())
            .build();
    }

    private List<UserEntity> createUserEntitiesList() {
        return List.of(createUserEntity(TEST_PERSON_1_EMAIL, TEST_PERSON_1_ID, TEST_PERSON_1_NAME),
            createUserEntity(TEST_PERSON_2_EMAIL, TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private UserEntity createUserEntity(String email, long id, String name) {
        return UserEntity.builder()
            .userId(id)
            .email(email)
            .personEntity(createPersonEntity(id, name))
            .build();
    }

    private List<PersonEntity> createPersonEntitiesList() {
        return List.of(createPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME),
            createPersonEntity(TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private HolidayCalculationPlanEntity createHolidayCalculationPlanEntity() {
        return HolidayCalculationPlanEntity.builder()
            .calculationPlanId(CALCULATION_PLAN_ID)
            .holidayTypes(PLAN)
            .description(DESCRIPTION)
            .build();
    }

    private HolidayCalculationPlan createHolidayCalculationPlan() {
        return HolidayCalculationPlan.builder()
            .calculationPlanId(CALCULATION_PLAN_ID)
            .holidayTypes(createCalculationPlaN())
            .description(DESCRIPTION)
            .build();
    }

    private SpecificPlan createSpecificPlan() {
        return SpecificPlan.builder()
            .build();
    }

    private Plan createCalculationPlaN() {
        return Plan.builder()
            .build();
    }

    @Test
    void testTransformShouldTransformEntitiesToPartialSelectorDomain() {
        //GIVEN
        List<UserEntity> entities = createUserEntitiesList();
        Set<PersonSelectorDomain> expected = createPersonSelectorDomains();
        given(partialPersonEntityTransformer.transform(entities)).willReturn(expected);
        //WHEN
        verifyNoInteractions(partialPersonEntityTransformer);
        Set<PersonSelectorDomain> actual = underTest.transformDetailsEntitiesToPartialSelectorDomain(entities);
        //THEN
        verify(partialPersonEntityTransformer).transform(entities);
        verifyNoMoreInteractions(partialPersonEntityTransformer);
        assertEquals(expected, actual);
    }

    @Test
    void testTransformShouldTransformFullEntityDetailsListToDomainSet() {
        //GIVEN
        List<PersonEntity> entities = createPersonEntitiesList();
        Set<PersonDetails> expected = createPersonDetailsDomainsSet();
        given(fullPersonEntityTransformer.transform(entities)).willReturn(expected);
        //WHEN
        verifyNoInteractions(fullPersonEntityTransformer);
        Set<PersonDetails> actual = underTest.transformFullEntityDetailsListToDomainSet(entities);
        //THEN
        verify(fullPersonEntityTransformer).transform(entities);
        verifyNoMoreInteractions(fullPersonEntityTransformer);
        assertEquals(expected, actual);
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

    @Test
    void testTransformShouldTransformFullDomainDetailsToEntity() {
        //GIVEN
        PersonDetails person = createPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        HolidayCalculationPlan calculationPlan = createHolidayCalculationPlan();
        PersonEntity expected = createPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        given(fullPersonEntityTransformer.transform(person, calculationPlan)).willReturn(expected);
        //WHEN
        verifyNoInteractions(fullPersonEntityTransformer);
        PersonEntity actual = underTest.transformFullDomainDetailsToEntity(person, calculationPlan);
        //THEN
        verify(fullPersonEntityTransformer).transform(person, calculationPlan);
        verifyNoMoreInteractions(fullPersonEntityTransformer);
        assertEquals(expected, actual);
    }

    @Test
    void testTransformShouldTransformFullDomainWithSpecificPlanToEntity() {
        //GIVEN
        PersonDetails person = createPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        SpecificPlan specificPlan = createSpecificPlan();
        PersonEntity expected = createPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        given(fullPersonEntityTransformer.transform(person, specificPlan)).willReturn(expected);
        //WHEN
        verifyNoInteractions(fullPersonEntityTransformer);
        PersonEntity actual = underTest.transformFullDomainWithSpecificPlanToEntity(person, specificPlan);
        //THEN
        verify(fullPersonEntityTransformer).transform(person, specificPlan);
        verifyNoMoreInteractions(fullPersonEntityTransformer);
        assertEquals(expected, actual);
    }

    @Test
    void testTransformShouldTransformFullDomainToEntity() {
        //GIVEN
        PersonDetails person = createPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        PersonEntity expected = createPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        given(fullPersonEntityTransformer.transform(person)).willReturn(expected);
        //WHEN
        verifyNoInteractions(fullPersonEntityTransformer);
        PersonEntity actual = underTest.transformFullDomainDetailsToEntity(person);
        //THEN
        verify(fullPersonEntityTransformer).transform(person);
        verifyNoMoreInteractions(fullPersonEntityTransformer);
        assertEquals(expected, actual);
    }

}
