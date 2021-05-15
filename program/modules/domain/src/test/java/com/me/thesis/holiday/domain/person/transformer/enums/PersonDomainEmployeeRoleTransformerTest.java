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

import com.me.thesis.holiday.domain.person.enums.PersonDomainEmployeeRole;
import com.me.thesis.holiday.dto.person.enums.PersonDtoEmployeeRole;

/**
 * The type Person domain employee role transformer test.
 */
class PersonDomainEmployeeRoleTransformerTest {

    private static final PersonDtoEmployeeRole PERSON_DTO_EMPLOYEE_ROLE_EMPLOYEE = PersonDtoEmployeeRole.EMPLOYEE;
    private static final PersonDtoEmployeeRole PERSON_DTO_EMPLOYEE_ROLE_SUPERVISOR = PersonDtoEmployeeRole.SUPERVISOR;
    private static final PersonDomainEmployeeRole PERSON_DOMAIN_EMPLOYEE_ROLE_EMPLOYEE = PersonDomainEmployeeRole.EMPLOYEE;
    private static final PersonDomainEmployeeRole PERSON_DOMAIN_EMPLOYEE_ROLE_SUPERVISOR = PersonDomainEmployeeRole.SUPERVISOR;

    @InjectMocks
    private PersonDomainEmployeeRoleTransformer underTest;

    @Mock
    private Map<PersonDtoEmployeeRole, PersonDomainEmployeeRole> personEmployeeRoleMap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformDtoToDomain() {
        //GIVEN
        when(personEmployeeRoleMap.get(PERSON_DTO_EMPLOYEE_ROLE_EMPLOYEE)).thenReturn(PERSON_DOMAIN_EMPLOYEE_ROLE_EMPLOYEE);
        //WHEN
        verifyNoInteractions(personEmployeeRoleMap);
        PersonDomainEmployeeRole actual = underTest.transform(PERSON_DTO_EMPLOYEE_ROLE_EMPLOYEE);
        //THEN
        verify(personEmployeeRoleMap).get(PERSON_DTO_EMPLOYEE_ROLE_EMPLOYEE);
        verifyNoMoreInteractions(personEmployeeRoleMap);
        assertEquals(PERSON_DOMAIN_EMPLOYEE_ROLE_EMPLOYEE, actual);
    }

    @Test
    void testTransformShouldTransformDtoToDomainSupervisor() {
        //GIVEN
        when(personEmployeeRoleMap.get(PERSON_DTO_EMPLOYEE_ROLE_SUPERVISOR)).thenReturn(PERSON_DOMAIN_EMPLOYEE_ROLE_SUPERVISOR);
        //WHEN
        verifyNoInteractions(personEmployeeRoleMap);
        PersonDomainEmployeeRole actual = underTest.transform(PERSON_DTO_EMPLOYEE_ROLE_SUPERVISOR);
        //THEN
        verify(personEmployeeRoleMap).get(PERSON_DTO_EMPLOYEE_ROLE_SUPERVISOR);
        verifyNoMoreInteractions(personEmployeeRoleMap);
        assertEquals(PERSON_DOMAIN_EMPLOYEE_ROLE_SUPERVISOR, actual);
    }

}
