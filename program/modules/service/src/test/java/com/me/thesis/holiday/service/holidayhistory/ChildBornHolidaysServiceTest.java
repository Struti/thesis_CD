package com.me.thesis.holiday.service.holidayhistory;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;
import com.me.thesis.holiday.service.lib.CurrentDateProvider;
import com.me.thesis.holiday.service.person.PersonService;

class ChildBornHolidaysServiceTest {

    private static final LocalDate CURRENT_DATE = LocalDate.of(2021, 4, 5);
    private static final PersonDomainGender MALE = PersonDomainGender.MALE;
    private static final PersonDomainGender FEMALE = PersonDomainGender.FEMALE;

    @InjectMocks
    private ChildBornHolidaysService underTest;

    @Mock
    private PersonService personService;

    @Mock
    private DadHolidaysHistoryService dadHolidaysHistoryService;

    @Mock
    private MaternityHolidayHistoryService maternityHolidayHistoryService;

    @Mock
    private CurrentDateProvider currentDateProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateShouldNotFailWithEmptyList() {
        //GIVEN
        List<ChildDetails> emptyList = Collections.emptyList();
        when(personService.getAllPersonDetails()).thenReturn(Collections.emptySet());
        when(currentDateProvider.provide()).thenReturn(CURRENT_DATE);
        //WHEN
        verifyNoInteractions(personService, dadHolidaysHistoryService, maternityHolidayHistoryService, currentDateProvider);
        underTest.validate();
        //THEN
        verify(personService).getAllPersonDetails();
        verify(currentDateProvider).provide();
        verify(dadHolidaysHistoryService).addHolidays(emptyList, CURRENT_DATE);
        verify(maternityHolidayHistoryService).addHolidays(emptyList, CURRENT_DATE);
        verifyNoMoreInteractions(personService, dadHolidaysHistoryService, maternityHolidayHistoryService, currentDateProvider);
    }

    @Test
    void testValidateShouldSortAndCall() {
        //GIVEN
        Set<PersonDetails> personDetails = createPersonDetails();
        List<ChildDetails> dadsChild = createDadsChild();
        List<ChildDetails> momsChild = createMomsChild();
        when(personService.getAllPersonDetails()).thenReturn(personDetails);
        when(currentDateProvider.provide()).thenReturn(CURRENT_DATE);
        //WHEN
        verifyNoInteractions(personService, dadHolidaysHistoryService, maternityHolidayHistoryService, currentDateProvider);
        underTest.validate();
        //THEN
        verify(personService).getAllPersonDetails();
        verify(currentDateProvider).provide();
        verify(dadHolidaysHistoryService).addHolidays(dadsChild, CURRENT_DATE);
        verify(maternityHolidayHistoryService).addHolidays(momsChild, CURRENT_DATE);
        verifyNoMoreInteractions(personService, dadHolidaysHistoryService, maternityHolidayHistoryService, currentDateProvider);
    }

    private List<ChildDetails> createDadsChild() {
        return createChildren();
    }

    private List<ChildDetails> createMomsChild() {
        return createExpectedChild();
    }

    private Set<PersonDetails> createPersonDetails() {
        return Set.of(createPerson(true, MALE, createChildren()), createPerson(true, FEMALE, createChildren()), createPerson(false, MALE,
            null), createPerson(false, FEMALE, null),
            createPerson(true, MALE, createExpectedChild()), createPerson(true, FEMALE, createExpectedChild()));
    }

    private List<ChildDetails> createExpectedChild() {
        return List.of(createChild(false));
    }

    private List<ChildDetails> createChildren() {
        return List.of(createChild(true));
    }

    private ChildDetails createChild(boolean isBorn) {
        return ChildDetails.builder()
            .born(isBorn)
            .build();
    }

    private PersonDetails createPerson(boolean child, PersonDomainGender gender, List<ChildDetails> children) {
        return PersonDetails.builder()
            .child(child)
            .gender(gender)
            .children(children)
            .build();
    }

}
