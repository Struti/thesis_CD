package com.me.thesis.holiday.service.holidayplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.holidayplan.domain.Plan;
import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;
import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;
import com.me.thesis.holiday.dto.holidayplan.HolidayType;
import com.me.thesis.holiday.service.holidaytypes.AgeHolidaysService;
import com.me.thesis.holiday.service.holidaytypes.UserDisabilityHolidaysService;

/**
 * The type Person specific plan factory test.
 */
class PersonSpecificPlanFactoryTest {

    private static final long ID = 111L;
    private static final int DAY = 20;
    private static final String TYPE = "type";
    private static final String DESCRIPTION = "desc";
    private static final String NAME = "name";
    private static final PersonDomainGender MALE = PersonDomainGender.MALE;
    private static final LocalDate BIRTH_DATE = LocalDate.of(1992, 3, 4);
    private static final String LOCATION = "location";
    private static final int PERSON_AGE = 29;
    private static final HolidayType USER_DISABILITY = HolidayType.USER_DISABILITY;
    private static final HolidayType CHILD_DISABILITY = HolidayType.CHILD_DISABILITY;
    private static final HolidayType CHILD = HolidayType.CHILD;
    private static final HolidayType AGE = HolidayType.AGE;
    private static final LocalDate CHILD_BIRTH_DATE = LocalDate.of(2020, 10, 11);
    private static final int CHILDREN_COUNT = 1;

    @InjectMocks
    private PersonSpecificPlanFactory underTest;

    @Mock
    private AgeHolidaysService ageHolidaysService;

    @Mock
    private UserDisabilityHolidaysService userDisabilityHolidaysService;

    @Mock
    private ChildRelatedPlanFragmentProvider childRelatedPlanFragmentProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateShouldCreateSpecificPlanWithBasicPlanOnly() {
        //GIVEN
        Plan plan = createPlanOnlyWithBasic();
        HolidayCalculationPlan calculationPlan = createCalculationPlan(plan);
        List<ChildDetails> children = Collections.emptyList();
        PersonDetails person = createPersonDetails(false, false, children, calculationPlan);
        SpecificPlan expected = createSpecificPlanOnlyWithBasic();
        //WHEN
        verifyNoInteractions(ageHolidaysService, userDisabilityHolidaysService, childRelatedPlanFragmentProvider);
        SpecificPlan actual = underTest.create(person, PERSON_AGE);
        //THEN
        verifyNoMoreInteractions(ageHolidaysService, userDisabilityHolidaysService, childRelatedPlanFragmentProvider);
        assertEquals(expected, actual);
    }

    private Plan createPlanOnlyWithBasic() {
        return Plan.builder()
            .basicHolidays(createBasicHolidays())
            .build();
    }

    private HolidayCalculationPlan createCalculationPlan(Plan plan) {
        return HolidayCalculationPlan.builder()
            .calculationPlanId(ID)
            .holidayTypes(plan)
            .description(DESCRIPTION)
            .build();
    }

    private PersonDetails createPersonDetails(boolean userDisability, boolean child, List<ChildDetails> children, HolidayCalculationPlan calculationPlan) {
        return PersonDetails.builder()
            .id(ID)
            .fullName(NAME)
            .birthName(NAME)
            .gender(MALE)
            .birthDate(BIRTH_DATE)
            .disability(userDisability)
            .child(child)
            .children(children)
            .calculationPlan(calculationPlan)
            .actualLocation(LOCATION)
            .build();
    }

    private SpecificPlan createSpecificPlanOnlyWithBasic() {
        return SpecificPlan.builder()
            .basicHolidays(createBasicHolidays())
            .build();
    }

    private BasicHolidays createBasicHolidays() {
        return BasicHolidays.builder()
            .id(ID)
            .day(DAY)
            .type(TYPE)
            .description(DESCRIPTION)
            .build();
    }

    @Test
    void testCreateShouldCreateSpecificPlan() {
        //GIVEN
        Plan plan = createPlan();
        HolidayCalculationPlan calculationPlan = createCalculationPlan(plan);
        List<ChildDetails> children = createChildren();
        PersonDetails person = createPersonDetails(true, true, children, calculationPlan);
        SpecificPlan expected = createSpecificPlan();
        ChildrenHolidays childHolidays = createChildHolidays();
        when(ageHolidaysService.getAllAgeBasedHolidayDetails()).thenReturn(createAgeBasedHolidays());
        when(userDisabilityHolidaysService.getAllUserDisabilityHolidays()).thenReturn(createUserDisabilityHolidays());
        when(childRelatedPlanFragmentProvider.getChildrenHoliday(children)).thenReturn(childHolidays);
        when(childRelatedPlanFragmentProvider.getChildDisabilityHoliday(children)).thenReturn(createChildDisabilityHoliday());
        //WHEN
        verifyNoInteractions(ageHolidaysService, userDisabilityHolidaysService, childRelatedPlanFragmentProvider);
        SpecificPlan actual = underTest.create(person, PERSON_AGE);
        //THEN
        verify(ageHolidaysService).getAllAgeBasedHolidayDetails();
        verify(userDisabilityHolidaysService).getAllUserDisabilityHolidays();
        verify(childRelatedPlanFragmentProvider).getChildrenHoliday(children);
        verify(childRelatedPlanFragmentProvider).getChildDisabilityHoliday(children);
        verifyNoMoreInteractions(ageHolidaysService, userDisabilityHolidaysService, childRelatedPlanFragmentProvider);
        assertEquals(expected, actual);
    }

    private Plan createPlan() {
        return Plan.builder()
            .basicHolidays(createBasicHolidays())
            .fixHolidays(createFixHolidays())
            .build();
    }

    private List<ChildDetails> createChildren() {
        return List.of(createChild());
    }

    private SpecificPlan createSpecificPlan() {
        return SpecificPlan.builder()
            .basicHolidays(createBasicHolidays())
            .ageBasedHolidays(createAgeBasedHoliday())
            .userDisabilityHolidays(createUserDisabilityHoliday())
            .childrenHolidays(createChildHolidays())
            .childDisabilityHolidays(createChildDisabilityHoliday())
            .build();
    }

    private ChildrenHolidays createChildHolidays() {
        return ChildrenHolidays.builder()
            .id(ID)
            .children(CHILDREN_COUNT)
            .day(DAY)
            .description(DESCRIPTION)
            .build();
    }

    private List<AgeBasedHolidays> createAgeBasedHolidays() {
        return List.of(createAgeBasedHoliday());
    }

    private List<UserDisabilityHolidays> createUserDisabilityHolidays() {
        return List.of(createUserDisabilityHoliday());
    }

    private ChildDisabilityHolidays createChildDisabilityHoliday() {
        return ChildDisabilityHolidays.builder()
            .id(ID)
            .type(CHILD_DISABILITY.getValue())
            .childrenNumber(CHILDREN_COUNT)
            .day(DAY)
            .description(DESCRIPTION)
            .build();
    }

    private List<HolidayType> createFixHolidays() {
        return List.of(AGE, CHILD, CHILD_DISABILITY, USER_DISABILITY);
    }

    private ChildDetails createChild() {
        return ChildDetails.builder()
            .childId(ID)
            .disability(false)
            .birthDate(CHILD_BIRTH_DATE)
            .build();
    }

    private AgeBasedHolidays createAgeBasedHoliday() {
        return AgeBasedHolidays.builder()
            .id(ID)
            .type(AGE.getValue())
            .description(DESCRIPTION)
            .age(PERSON_AGE)
            .day(DAY)
            .build();
    }

    private UserDisabilityHolidays createUserDisabilityHoliday() {
        return UserDisabilityHolidays.builder()
            .id(ID)
            .type(USER_DISABILITY.getValue())
            .day(DAY)
            .description(DESCRIPTION)
            .build();
    }

}
