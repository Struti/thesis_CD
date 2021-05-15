package com.me.thesis.holiday.service.person.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidayplan.domain.HolidayCalculationPlanEntity;
import com.me.thesis.holiday.dal.person.dao.PersonDao;
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.enums.PersonEntityEmployeeRole;
import com.me.thesis.holiday.dal.person.enums.PersonEntityGender;
import com.me.thesis.holiday.dal.person.enums.PersonEntityTitle;
import com.me.thesis.holiday.dal.person.transformer.facade.PersonEntityTransformerFacade;
import com.me.thesis.holiday.dal.user.dao.UserDao;
import com.me.thesis.holiday.dal.user.domain.UserEntity;
import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.holidayplan.domain.Plan;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.PersonSelectorDomain;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;
import com.me.thesis.holiday.domain.person.enums.PersonDomainEmployeeRole;
import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;
import com.me.thesis.holiday.domain.person.enums.PersonDomainTitle;
import com.me.thesis.holiday.service.holidayplan.HolidayCalculationPlanService;
import com.me.thesis.holiday.service.holidayplan.PersonSpecificPlanFactory;
import com.me.thesis.holiday.service.lib.CurrentDateProvider;
import com.me.thesis.holiday.service.person.PersonService;

/**
 * The type Person service test.
 */
class PersonServiceTest {

    private static final long TEST_PERSON_2_ID = 809L;
    private static final long TEST_PERSON_1_ID = 9181L;
    private static final String TEST_PERSON_2_NAME = "Theódóra Ryba";
    private static final String ACTUAL_LOCATION = "Románia, Nagyvárad";
    private static final String TEST_PERSON_1_NAME = "Ananth Zapatero";
    private static final String TEST_PERSON_2_EMAIL = "Theodora.ryba@test.com";
    private static final String TEST_PERSON_1_EMAIL = "ananth.zapatero@test.com";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1973, 5, 6);
    private static final LocalDate START_DATE_AT_WORK = LocalDate.of(2014, 10, 3);
    private static final long CALCULATION_PLAN_ID = 1111L;
    private static final String DESCRIPTION = "desc";
    private static final String HOLIDAY_TYPES = "PLAN";
    private static final int PERSON_AGE = 48;
    private static final LocalDate CURRENT_DATE = LocalDate.of(2021, 3, 3);

    @InjectMocks
    private PersonService underTest;

    @Mock
    private PersonEntityTransformerFacade transformer;

    @Mock
    private UserDao userDao;

    @Mock
    private PersonDao personDao;

    @Mock
    private CurrentDateProvider currentDateProvider;

    @Mock
    private PersonSpecificPlanFactory specificPlanFactory;

    @Mock
    private HolidayCalculationPlanService calculationPlanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPersonSelectorsShouldCallTransformerAndUserDaoInOrder() {
        //GIVEN
        List<UserEntity> userEntities = createUserEntities();
        Set<PersonSelectorDomain> domains = createPersonSelectorDomains();
        Set<PersonSelectorDomain> expected = createPersonSelectorDomains();
        given(userDao.findAllUser()).willReturn(userEntities);
        given(transformer.transformDetailsEntitiesToPartialSelectorDomain(userEntities)).willReturn(domains);
        //WHEN
        verifyNoInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
        Set<PersonSelectorDomain> actual = underTest.getPersonSelectors();
        //THEN
        verify(userDao).findAllUser();
        verify(transformer).transformDetailsEntitiesToPartialSelectorDomain(userEntities);
        verifyNoMoreInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
        assertEquals(expected, actual);
    }

    @Test
    void testGetPersonByIdShouldReturnWithDomain() {
        //GIVEN
        PersonEntity personEntity = createFullDetailsPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        PersonDetails expected = createFullPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        when(personDao.findById(TEST_PERSON_1_ID)).thenReturn(personEntity);
        when(transformer.transformFullEntityDetailsToDomain(personEntity)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
        PersonDetails actual = underTest.getPersonById(TEST_PERSON_1_ID);
        //THEN
        verify(personDao).findById(TEST_PERSON_1_ID);
        verify(transformer).transformFullEntityDetailsToDomain(personEntity);
        verifyNoMoreInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
        assertEquals(expected, actual);
    }

    private PersonDetails createFullPersonDetailsDomain(long id, String name) {
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

    private PersonEntity createFullDetailsPersonEntity(long id, String name) {
        return PersonEntity.builder()
            .personId(id)
            .title(PersonEntityTitle.NONE)
            .fullName(name)
            .birthName(name)
            .gender(PersonEntityGender.FEMALE)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .child(false)
            .startDate(START_DATE_AT_WORK)
            .lastDay(LocalDate.MAX)
            .employeeRole(PersonEntityEmployeeRole.SUPERVISOR)
            .actualLocation(ACTUAL_LOCATION)
            .holidayCalculationPlanEntity(createHolidayCalculationPlanEntity())
            .build();
    }

    private HolidayCalculationPlanEntity createHolidayCalculationPlanEntity() {
        return HolidayCalculationPlanEntity.builder()
            .calculationPlanId(CALCULATION_PLAN_ID)
            .description(DESCRIPTION)
            .holidayTypes(HOLIDAY_TYPES)
            .build();
    }

    @Test
    void testSavePersonShouldSave() {
        //GIVEN
        PersonDetails domain = createFullPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        PersonEntity entity = createFullDetailsPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        HolidayCalculationPlan calculationPlan = createCalculationPlan();
        when(calculationPlanService.getPlanById(CALCULATION_PLAN_ID)).thenReturn(calculationPlan);
        when(transformer.transformFullDomainDetailsToEntity(domain, calculationPlan)).thenReturn(entity);
        when(personDao.savePerson(entity)).thenReturn(entity);
        //WHEN
        verifyNoInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
        underTest.savePerson(domain, CALCULATION_PLAN_ID);
        //THEN
        verify(calculationPlanService).getPlanById(CALCULATION_PLAN_ID);
        verify(transformer).transformFullDomainDetailsToEntity(domain, calculationPlan);
        verify(personDao).savePerson(entity);
        verifyNoMoreInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
    }

    private HolidayCalculationPlan createCalculationPlan() {
        return HolidayCalculationPlan.builder()
            .calculationPlanId(CALCULATION_PLAN_ID)
            .description(DESCRIPTION)
            .holidayTypes(createHolidayCalculationPlan())
            .build();
    }

    private Plan createHolidayCalculationPlan() {
        return Plan.builder()
            .build();
    }

    @Test
    void testDeletePersonShouldDelete() {
        //GIVEN
        //WHEN
        verifyNoInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
        underTest.deletePerson(TEST_PERSON_1_ID);
        //THEN
        verify(personDao).deletePerson(TEST_PERSON_1_ID);
        verifyNoMoreInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
    }

    @Test
    void testGetAllPersonDetailsShouldCallTransformerAndDaoInOrderAndReturnWithDomainSet() {
        //GIVEN
        List<PersonEntity> entities = createPersonEntities();
        Set<PersonDetails> expected = createPersonDetailsDomains();
        when(personDao.findAllPersonDetails()).thenReturn(entities);
        when(transformer.transformFullEntityDetailsListToDomainSet(entities)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
        Set<PersonDetails> actual = underTest.getAllPersonDetails();
        //THEN
        verify(personDao).findAllPersonDetails();
        verify(transformer).transformFullEntityDetailsListToDomainSet(entities);
        verifyNoMoreInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
        assertEquals(expected, actual);
    }

    private Set<PersonDetails> createPersonDetailsDomains() {
        return Set.of(createFullPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME),
            createFullPersonDetailsDomain(TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private List<UserEntity> createUserEntities() {
        return List.of(createUserEntity(TEST_PERSON_1_EMAIL, createPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME)),
            createUserEntity(TEST_PERSON_2_EMAIL, createPersonEntity(TEST_PERSON_2_ID, TEST_PERSON_2_NAME)));
    }

    private List<PersonEntity> createPersonEntities() {
        return List.of(createFullDetailsPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME),
            createFullDetailsPersonEntity(TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private UserEntity createUserEntity(String email, PersonEntity personEntity) {
        return UserEntity.builder()
            .email(email)
            .personEntity(personEntity)
            .build();
    }

    @Test
    void testShouldFindPersonById() {
        //GIVEN
        PersonEntity entity = createPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        PersonDetails expected = createFullPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        when(personDao.findById(TEST_PERSON_1_ID)).thenReturn(entity);
        when(transformer.transformFullEntityDetailsToDomain(entity)).thenReturn(expected);
        //WHEN
        verifyNoInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
        PersonDetails actual = underTest.findPersonById(TEST_PERSON_1_ID);
        //THEN
        verify(personDao).findById(TEST_PERSON_1_ID);
        verify(transformer).transformFullEntityDetailsToDomain(entity);
        verifyNoMoreInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
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

    private PersonEntity createPersonEntity(long id, String name) {
        return PersonEntity.builder()
            .fullName(name)
            .personId(id)
            .birthDate(BIRTH_DATE)
            .build();
    }

    @Test
    void testShouldSaveWithSpecificPlan() {
        //GIVEN
        PersonEntity entity = createPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        PersonDetails domain = createFullPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        SpecificPlan specificPlan = createSpecificPlan();
        when(personDao.findById(TEST_PERSON_1_ID)).thenReturn(entity);
        when(transformer.transformFullEntityDetailsToDomain(entity)).thenReturn(domain);
        when(currentDateProvider.provide()).thenReturn(CURRENT_DATE);
        when(specificPlanFactory.create(domain, PERSON_AGE)).thenReturn(specificPlan);
        when(transformer.transformFullDomainWithSpecificPlanToEntity(domain, specificPlan)).thenReturn(entity);
        //WHEN
        verifyNoInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
        underTest.specifyPlan(TEST_PERSON_1_ID);
        //THEN
        verify(personDao).findById(TEST_PERSON_1_ID);
        verify(transformer).transformFullEntityDetailsToDomain(entity);
        verify(currentDateProvider).provide();
        verify(specificPlanFactory).create(domain, PERSON_AGE);
        verify(transformer).transformFullDomainWithSpecificPlanToEntity(domain, specificPlan);
        verify(personDao).savePerson(entity);
        verifyNoMoreInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
    }

    private SpecificPlan createSpecificPlan() {
        return SpecificPlan.builder()
            .build();
    }

    @Test
    void testGetPersonAgeShouldReturnPersonage() {
        //GIVEN
        PersonDetails domain = createFullPersonDetailsDomain(TEST_PERSON_1_ID, TEST_PERSON_1_NAME);
        when(currentDateProvider.provide()).thenReturn(CURRENT_DATE);
        //WHEN
        verifyNoInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
        int actual = underTest.getPersonAge(domain);
        //THEN
        verify(currentDateProvider).provide();
        verifyNoMoreInteractions(personDao, userDao, transformer, currentDateProvider, specificPlanFactory, calculationPlanService);
        assertEquals(PERSON_AGE, actual);
    }

}
