package com.me.thesis.holiday.service.scheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;
import com.me.thesis.holiday.service.person.PersonService;

/**
 * The type Yearly plan specification scheduler test.
 */
class YearlyPlanSpecificationSchedulerTest {

    private static final long ID = 11111111L;

    @InjectMocks
    private YearlyPlanSpecificationScheduler underTest;

    @Mock
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdatePlansShouldUpdatePlans() {
        //GIVEN
        Set<PersonDetails> persons = createPersons();
        //WHEN
        verifyNoInteractions(personService);
        underTest.updatePlans(persons);
        //THEN
        verify(personService).specifyPlan(ID);
        verifyNoMoreInteractions(personService);
    }

    private Set<PersonDetails> createPersons() {
        return Set.of(createPerson());
    }

    private PersonDetails createPerson() {
        return PersonDetails.builder()
            .id(ID)
            .specificPlan(createSpecificPlan())
            .build();
    }

    private SpecificPlan createSpecificPlan() {
        return SpecificPlan.builder()
            .build();
    }

}
