package com.me.thesis.holiday.dal.person.transformer.fulltransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
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
import com.me.thesis.holiday.dal.holidayplan.transfromer.HolidayCalculationPlanEntityTransformer;
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.enums.PersonEntityEmployeeRole;
import com.me.thesis.holiday.dal.person.enums.PersonEntityGender;
import com.me.thesis.holiday.dal.person.enums.PersonEntityTitle;
import com.me.thesis.holiday.dal.person.transformer.enums.PersonEntityEmployeeRoleTransformer;
import com.me.thesis.holiday.dal.person.transformer.enums.PersonEntityGenderTransformer;
import com.me.thesis.holiday.dal.person.transformer.enums.PersonEntityTitleTransformer;
import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;
import com.me.thesis.holiday.domain.person.enums.PersonDomainEmployeeRole;
import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;
import com.me.thesis.holiday.domain.person.enums.PersonDomainTitle;

/**
 * The type Full person entity transformer test.
 */
class FullPersonEntityTransformerTest {

    private static final long TEST_PERSON_2_ID = 809L;
    private static final long TEST_PERSON_1_ID = 9181L;
    private static final String TEST_PERSON_2_NAME = "Theódóra Ryba";
    private static final String ACTUAL_LOCATION = "Románia, Nagyvárad";
    private static final String TEST_PERSON_1_NAME = "Ananth Zapatero";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1973, 5, 6);
    private static final LocalDate START_DATE_AT_WORK = LocalDate.of(2014, 10, 3);
    private static final PersonEntityTitle PERSON_ENTITY_TITLE = PersonEntityTitle.DR1;
    private static final PersonDomainTitle PERSON_DOMAIN_TITLE = PersonDomainTitle.DR1;
    private static final PersonEntityGender PERSON_ENTITY_GENDER = PersonEntityGender.MALE;
    private static final PersonDomainGender PERSON_DOMAIN_GENDER = PersonDomainGender.MALE;
    private static final PersonDomainEmployeeRole PERSON_DOMAIN_EMPLOYEE_ROLE = PersonDomainEmployeeRole.EMPLOYEE;
    private static final PersonEntityEmployeeRole PERSON_ENTITY_EMPLOYEE_ROLE = PersonEntityEmployeeRole.EMPLOYEE;
    private static final String SPECIFIC_PLAN = "Specific plan";
    private static final String HOLIDAY_TYPES = "types";

    @InjectMocks
    private FullPersonEntityTransformer underTest;

    @Mock
    private PersonEntityEmployeeRoleTransformer roleTransformer;

    @Mock
    private PersonEntityGenderTransformer genderTransformer;

    @Mock
    private PersonEntityTitleTransformer titleTransformer;

    @Mock
    private HolidayCalculationPlanEntityTransformer holidayCalculationPlanEntityTransformer;

    @Mock
    private SpecialPlanTransformer specialPlanTransformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformEntityDetailsListToDomainsSet() {
        //GIVEN
        List<PersonEntity> entities = createPersonEntities();
        Set<PersonDetails> expected = createPersonDetailsDomainsSet();
        HolidayCalculationPlan calculationPlan = createHolidayCalculationPlan();
        HolidayCalculationPlanEntity calculationPlanEntity = createHolidayCalculationPlanEntity();
        SpecificPlan specialPlan = createSpecialPlan();
        given(titleTransformer.transform(PERSON_ENTITY_TITLE)).willReturn(PERSON_DOMAIN_TITLE);
        given(genderTransformer.transform(PERSON_ENTITY_GENDER)).willReturn(PERSON_DOMAIN_GENDER);
        given(roleTransformer.transform(PERSON_ENTITY_EMPLOYEE_ROLE)).willReturn(PERSON_DOMAIN_EMPLOYEE_ROLE);
        given(holidayCalculationPlanEntityTransformer.transform(calculationPlanEntity)).willReturn(calculationPlan);
        given(specialPlanTransformer.transform(SPECIFIC_PLAN)).willReturn(specialPlan);
        //WHEN
        verifyNoInteractions(roleTransformer, genderTransformer, titleTransformer, holidayCalculationPlanEntityTransformer, specialPlanTransformer);
        Set<PersonDetails> actual = underTest.transform(entities);
        //THEN
        verify(titleTransformer, times(2)).transform(PERSON_ENTITY_TITLE);
        verify(genderTransformer, times(2)).transform(PERSON_ENTITY_GENDER);
        verify(roleTransformer, times(2)).transform(PERSON_ENTITY_EMPLOYEE_ROLE);
        verify(holidayCalculationPlanEntityTransformer, times(2)).transform(calculationPlanEntity);
        verify(specialPlanTransformer, times(2)).transform(SPECIFIC_PLAN);
        verifyNoMoreInteractions(roleTransformer, genderTransformer, titleTransformer, holidayCalculationPlanEntityTransformer, specialPlanTransformer);
        assertEquals(expected, actual);
    }

    private HolidayCalculationPlan createHolidayCalculationPlan() {
        return HolidayCalculationPlan.builder()
            .build();
    }

    private HolidayCalculationPlanEntity createHolidayCalculationPlanEntity() {
        return HolidayCalculationPlanEntity.builder()
            .holidayTypes(HOLIDAY_TYPES)
            .build();
    }

    private SpecificPlan createSpecialPlan() {
        return SpecificPlan.builder()
            .build();
    }

    private Set<PersonDetails> createPersonDetailsDomainsSet() {
        return Set.of(createPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME),
            createPersonDetailsDomain(TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    @Test
    void testTransformDomainDetailsToEntity() {
        //GIVEN
        PersonDetails domain = createPersonDetailsDomainWithoutPlans();
        PersonEntity expected = createPersonEntityWithoutPlans();
        given(titleTransformer.transform(PERSON_DOMAIN_TITLE)).willReturn(PERSON_ENTITY_TITLE);
        given(genderTransformer.transform(PERSON_DOMAIN_GENDER)).willReturn(PERSON_ENTITY_GENDER);
        given(roleTransformer.transform(PERSON_DOMAIN_EMPLOYEE_ROLE)).willReturn(PERSON_ENTITY_EMPLOYEE_ROLE);
        //WHEN
        verifyNoInteractions(roleTransformer, genderTransformer, titleTransformer, holidayCalculationPlanEntityTransformer, specialPlanTransformer);
        PersonEntity actual = underTest.transform(domain);
        //THEN
        verify(titleTransformer).transform(PERSON_DOMAIN_TITLE);
        verify(genderTransformer).transform(PERSON_DOMAIN_GENDER);
        verify(roleTransformer).transform(PERSON_DOMAIN_EMPLOYEE_ROLE);

        verifyNoMoreInteractions(roleTransformer, genderTransformer, titleTransformer, holidayCalculationPlanEntityTransformer, specialPlanTransformer);
        assertEquals(expected, actual);
    }

    private PersonDetails createPersonDetailsDomainWithoutPlans() {
        return PersonDetails.builder()
            .id(TEST_PERSON_1_ID)
            .title(PERSON_DOMAIN_TITLE)
            .fullName(TEST_PERSON_1_NAME)
            .birthName(TEST_PERSON_1_NAME)
            .gender(PERSON_DOMAIN_GENDER)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .child(false)
            .startDate(START_DATE_AT_WORK)
            .lastDay(LocalDate.MAX)
            .employeeRole(PERSON_DOMAIN_EMPLOYEE_ROLE)
            .actualLocation(ACTUAL_LOCATION)
            .build();
    }

    private List<PersonEntity> createPersonEntities() {
        return List.of(createPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME),
            createPersonEntity(TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private PersonEntity createPersonEntityWithoutPlans() {
        return PersonEntity.builder()
            .personId(TEST_PERSON_1_ID)
            .title(PERSON_ENTITY_TITLE)
            .fullName(TEST_PERSON_1_NAME)
            .birthName(TEST_PERSON_1_NAME)
            .gender(PERSON_ENTITY_GENDER)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .child(false)
            .startDate(START_DATE_AT_WORK)
            .lastDay(LocalDate.MAX)
            .employeeRole(PERSON_ENTITY_EMPLOYEE_ROLE)
            .actualLocation(ACTUAL_LOCATION)
            .build();
    }

    @Test
    void testTransformDomainDetailsToEntityWithCalculationPlans() {
        //GIVEN
        PersonDetails domain = createPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        PersonEntity expected = createPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        HolidayCalculationPlan calculationPlan = domain.getCalculationPlan()
            .orElse(null);
        HolidayCalculationPlanEntity calculationPlanEntity = expected.getHolidayCalculationPlanEntity();
        SpecificPlan specialPlan = domain.getSpecificPlan()
            .orElse(null);
        given(titleTransformer.transform(PERSON_DOMAIN_TITLE)).willReturn(PERSON_ENTITY_TITLE);
        given(genderTransformer.transform(PERSON_DOMAIN_GENDER)).willReturn(PERSON_ENTITY_GENDER);
        given(roleTransformer.transform(PERSON_DOMAIN_EMPLOYEE_ROLE)).willReturn(PERSON_ENTITY_EMPLOYEE_ROLE);
        given(holidayCalculationPlanEntityTransformer.transform(calculationPlan)).willReturn(calculationPlanEntity);
        given(specialPlanTransformer.transform(specialPlan)).willReturn(SPECIFIC_PLAN);
        //WHEN
        verifyNoInteractions(roleTransformer, genderTransformer, titleTransformer, holidayCalculationPlanEntityTransformer, specialPlanTransformer);
        PersonEntity actual = underTest.transform(domain, calculationPlan);
        //THEN
        verify(titleTransformer).transform(PERSON_DOMAIN_TITLE);
        verify(genderTransformer).transform(PERSON_DOMAIN_GENDER);
        verify(roleTransformer).transform(PERSON_DOMAIN_EMPLOYEE_ROLE);
        verify(holidayCalculationPlanEntityTransformer, times(2)).transform(calculationPlan);
        verify(specialPlanTransformer).transform(specialPlan);
        verifyNoMoreInteractions(roleTransformer, genderTransformer, titleTransformer, holidayCalculationPlanEntityTransformer, specialPlanTransformer);
        assertEquals(expected, actual);
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
            .specificPlan(createSpecialPlan())
            .build();
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

    @Test
    void testTransformDomainDetailsToEntitySpecialPlans() {
        //GIVEN
        PersonDetails domain = createPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        PersonEntity expected = createPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        HolidayCalculationPlan calculationPlan = domain.getCalculationPlan()
            .orElse(null);
        HolidayCalculationPlanEntity calculationPlanEntity = expected.getHolidayCalculationPlanEntity();
        SpecificPlan specialPlan = domain.getSpecificPlan()
            .orElse(null);
        given(titleTransformer.transform(PERSON_DOMAIN_TITLE)).willReturn(PERSON_ENTITY_TITLE);
        given(genderTransformer.transform(PERSON_DOMAIN_GENDER)).willReturn(PERSON_ENTITY_GENDER);
        given(roleTransformer.transform(PERSON_DOMAIN_EMPLOYEE_ROLE)).willReturn(PERSON_ENTITY_EMPLOYEE_ROLE);
        given(holidayCalculationPlanEntityTransformer.transform(calculationPlan)).willReturn(calculationPlanEntity);
        given(specialPlanTransformer.transform(specialPlan)).willReturn(SPECIFIC_PLAN);
        //WHEN
        verifyNoInteractions(roleTransformer, genderTransformer, titleTransformer, holidayCalculationPlanEntityTransformer, specialPlanTransformer);
        PersonEntity actual = underTest.transform(domain, specialPlan);
        //THEN
        verify(titleTransformer).transform(PERSON_DOMAIN_TITLE);
        verify(genderTransformer).transform(PERSON_DOMAIN_GENDER);
        verify(roleTransformer).transform(PERSON_DOMAIN_EMPLOYEE_ROLE);
        verify(holidayCalculationPlanEntityTransformer).transform(calculationPlan);
        verify(specialPlanTransformer, times(2)).transform(specialPlan);
        verifyNoMoreInteractions(roleTransformer, genderTransformer, titleTransformer, holidayCalculationPlanEntityTransformer, specialPlanTransformer);
        assertEquals(expected, actual);
    }

    @Test
    void testTransformEntityDetailsToDomain() {
        //GIVEN
        PersonEntity entity = createPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        PersonDetails expected = createPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        HolidayCalculationPlan calculationPlan = createHolidayCalculationPlan();
        HolidayCalculationPlanEntity calculationPlanEntity = createHolidayCalculationPlanEntity();
        SpecificPlan specialPlan = createSpecialPlan();
        given(titleTransformer.transform(PERSON_ENTITY_TITLE)).willReturn(PERSON_DOMAIN_TITLE);
        given(genderTransformer.transform(PERSON_ENTITY_GENDER)).willReturn(PERSON_DOMAIN_GENDER);
        given(roleTransformer.transform(PERSON_ENTITY_EMPLOYEE_ROLE)).willReturn(PERSON_DOMAIN_EMPLOYEE_ROLE);
        given(holidayCalculationPlanEntityTransformer.transform(calculationPlanEntity)).willReturn(calculationPlan);
        given(specialPlanTransformer.transform(SPECIFIC_PLAN)).willReturn(specialPlan);
        //WHEN
        verifyNoInteractions(roleTransformer, genderTransformer, titleTransformer, holidayCalculationPlanEntityTransformer, specialPlanTransformer);
        PersonDetails actual = underTest.transform(entity);
        //THEN
        verify(titleTransformer).transform(PERSON_ENTITY_TITLE);
        verify(genderTransformer).transform(PERSON_ENTITY_GENDER);
        verify(roleTransformer).transform(PERSON_ENTITY_EMPLOYEE_ROLE);
        verify(holidayCalculationPlanEntityTransformer).transform(calculationPlanEntity);
        verify(specialPlanTransformer).transform(SPECIFIC_PLAN);
        verifyNoMoreInteractions(roleTransformer, genderTransformer, titleTransformer, holidayCalculationPlanEntityTransformer, specialPlanTransformer);
        assertEquals(expected, actual);
    }
}
