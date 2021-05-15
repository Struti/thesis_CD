package com.me.thesis.holiday.domain.person.transformer.fulltransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.enums.PersonDomainEmployeeRole;
import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;
import com.me.thesis.holiday.domain.person.enums.PersonDomainTitle;
import com.me.thesis.holiday.domain.person.transformer.enums.PersonDomainEmployeeRoleTransformer;
import com.me.thesis.holiday.domain.person.transformer.enums.PersonDomainGenderTransformer;
import com.me.thesis.holiday.domain.person.transformer.enums.PersonDomainTitleTransformer;
import com.me.thesis.holiday.dto.person.domain.PersonDetailsDto;
import com.me.thesis.holiday.dto.person.enums.PersonDtoEmployeeRole;
import com.me.thesis.holiday.dto.person.enums.PersonDtoGender;
import com.me.thesis.holiday.dto.person.enums.PersonDtoTitle;

/**
 * The type Full person domain transformer test.
 */
class FullPersonDomainTransformerTest {

    private static final long TEST_PERSON_1_ID = 9181L;
    private static final String ACTUAL_LOCATION = "Románia, Nagyvárad";
    private static final String TEST_PERSON_1_NAME = "Ananth Zapatero";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1973, 5, 6);
    private static final LocalDate START_DATE_AT_WORK = LocalDate.of(2014, 10, 3);

    @InjectMocks
    private FullPersonDomainTransformer underTest;

    @Mock
    private PersonDomainTitleTransformer titleTransformer;

    @Mock
    private PersonDomainGenderTransformer genderTransformer;

    @Mock
    private PersonDomainEmployeeRoleTransformer employeeRoleTransformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldCallTransformersInOrderAndCreateDomain() {
        //GIVEN
        InOrder inOrder = Mockito.inOrder(titleTransformer, genderTransformer, employeeRoleTransformer);
        PersonDetailsDto dto = createPersonDto();
        PersonDetails expected = createPersonDetailsDomain();

        given(titleTransformer.transform(PersonDtoTitle.DR1)).willReturn(PersonDomainTitle.DR1);
        given(genderTransformer.transform(PersonDtoGender.MALE)).willReturn(PersonDomainGender.MALE);
        given(employeeRoleTransformer.transform(PersonDtoEmployeeRole.EMPLOYEE)).willReturn(PersonDomainEmployeeRole.EMPLOYEE);
        //WHEN
        verifyNoInteractions(titleTransformer, genderTransformer, employeeRoleTransformer);
        PersonDetails actual = underTest.transform(dto);
        //THEN
        inOrder.verify(titleTransformer, times(1))
            .transform(PersonDtoTitle.DR1);
        inOrder.verify(genderTransformer, times(1))
            .transform(PersonDtoGender.MALE);
        inOrder.verify(employeeRoleTransformer, times(1))
            .transform(PersonDtoEmployeeRole.EMPLOYEE);
        verifyNoMoreInteractions(titleTransformer, genderTransformer, employeeRoleTransformer);
        assertEquals(expected, actual);
        assertEquals(expected.toString(), actual.toString());
    }

    private PersonDetailsDto createPersonDto() {
        return PersonDetailsDto.builder()
            .id(TEST_PERSON_1_ID)
            .title(PersonDtoTitle.DR1)
            .fullName(TEST_PERSON_1_NAME)
            .birthName(TEST_PERSON_1_NAME)
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

    private PersonDetails createPersonDetailsDomain() {
        return PersonDetails.builder()
            .id(TEST_PERSON_1_ID)
            .title(PersonDomainTitle.DR1)
            .fullName(TEST_PERSON_1_NAME)
            .birthName(TEST_PERSON_1_NAME)
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

}
