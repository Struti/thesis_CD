package com.me.thesis.holiday.dal.person.transformer.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
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

import com.me.thesis.holiday.dal.person.enums.PersonEntityEmployeeRole;
import com.me.thesis.holiday.domain.person.enums.PersonDomainEmployeeRole;

/**
 * The type Person entity employee role transformer test.
 */
class PersonEntityEmployeeRoleTransformerTest {

    private static final PersonDomainEmployeeRole PERSON_DOMAIN_EMPLOYEE_ROLE = PersonDomainEmployeeRole.EMPLOYEE;
    private static final PersonEntityEmployeeRole PERSON_ENTITY_EMPLOYEE_ROLE = PersonEntityEmployeeRole.EMPLOYEE;
    private static final PersonDomainEmployeeRole PERSON_DOMAIN_EMPLOYEE_ROLE_SUPERVISOR = PersonDomainEmployeeRole.SUPERVISOR;
    private static final PersonEntityEmployeeRole PERSON_ENTITY_EMPLOYEE_ROLE_SUPERVISOR = PersonEntityEmployeeRole.SUPERVISOR;

    @InjectMocks
    private PersonEntityEmployeeRoleTransformer underTest;

    @Mock
    private Map<PersonEntityEmployeeRole, PersonDomainEmployeeRole> personEmployeeRoleEntityToDomainMap;

    @Mock
    private Map<PersonDomainEmployeeRole, PersonEntityEmployeeRole> personEmployeeRoleDomainToEntityMap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformDomainToEntity() {
        //GIVEN
        when(personEmployeeRoleDomainToEntityMap.getOrDefault(PERSON_DOMAIN_EMPLOYEE_ROLE, PERSON_ENTITY_EMPLOYEE_ROLE)).thenReturn(PERSON_ENTITY_EMPLOYEE_ROLE);
        //WHEN
        verifyNoInteractions(personEmployeeRoleDomainToEntityMap);
        PersonEntityEmployeeRole actual = underTest.transform(PERSON_DOMAIN_EMPLOYEE_ROLE);
        //THEN
        verify(personEmployeeRoleDomainToEntityMap, times(1)).getOrDefault(PERSON_DOMAIN_EMPLOYEE_ROLE, PERSON_ENTITY_EMPLOYEE_ROLE);
        verifyNoMoreInteractions(personEmployeeRoleDomainToEntityMap);
        assertEquals(PERSON_ENTITY_EMPLOYEE_ROLE, actual);
    }

    @Test
    void testTransformShouldTransformDomainToEntitySupervisor() {
        //GIVEN
        when(personEmployeeRoleDomainToEntityMap.getOrDefault(PERSON_DOMAIN_EMPLOYEE_ROLE_SUPERVISOR, PERSON_ENTITY_EMPLOYEE_ROLE))
            .thenReturn(PERSON_ENTITY_EMPLOYEE_ROLE_SUPERVISOR);
        //WHEN
        verifyNoInteractions(personEmployeeRoleDomainToEntityMap);
        PersonEntityEmployeeRole actual = underTest.transform(PERSON_DOMAIN_EMPLOYEE_ROLE_SUPERVISOR);
        //THEN
        verify(personEmployeeRoleDomainToEntityMap, times(1)).getOrDefault(PERSON_DOMAIN_EMPLOYEE_ROLE_SUPERVISOR, PERSON_ENTITY_EMPLOYEE_ROLE);
        verifyNoMoreInteractions(personEmployeeRoleDomainToEntityMap);
        assertEquals(PERSON_ENTITY_EMPLOYEE_ROLE_SUPERVISOR, actual);
    }

    @Test
    void testTransformShouldTransformEntityToDomain() {
        //GIVEN
        when(personEmployeeRoleEntityToDomainMap.getOrDefault(PERSON_ENTITY_EMPLOYEE_ROLE, PERSON_DOMAIN_EMPLOYEE_ROLE)).thenReturn(PERSON_DOMAIN_EMPLOYEE_ROLE);
        //WHEN
        verifyNoInteractions(personEmployeeRoleEntityToDomainMap);
        PersonDomainEmployeeRole actual = underTest.transform(PERSON_ENTITY_EMPLOYEE_ROLE);
        //THEN
        verify(personEmployeeRoleEntityToDomainMap, times(1)).getOrDefault(PERSON_ENTITY_EMPLOYEE_ROLE, PERSON_DOMAIN_EMPLOYEE_ROLE);
        verifyNoMoreInteractions(personEmployeeRoleEntityToDomainMap);
        assertEquals(PERSON_DOMAIN_EMPLOYEE_ROLE, actual);
    }

    @Test
    void testTransformShouldTransformEntityToDomainSupervisor() {
        //GIVEN
        when(personEmployeeRoleEntityToDomainMap.getOrDefault(PERSON_ENTITY_EMPLOYEE_ROLE_SUPERVISOR, PERSON_DOMAIN_EMPLOYEE_ROLE))
            .thenReturn(PERSON_DOMAIN_EMPLOYEE_ROLE_SUPERVISOR);
        //WHEN
        verifyNoInteractions(personEmployeeRoleEntityToDomainMap);
        PersonDomainEmployeeRole actual = underTest.transform(PERSON_ENTITY_EMPLOYEE_ROLE_SUPERVISOR);
        //THEN
        verify(personEmployeeRoleEntityToDomainMap, times(1)).getOrDefault(PERSON_ENTITY_EMPLOYEE_ROLE_SUPERVISOR, PERSON_DOMAIN_EMPLOYEE_ROLE);
        verifyNoMoreInteractions(personEmployeeRoleEntityToDomainMap);
        assertEquals(PERSON_DOMAIN_EMPLOYEE_ROLE_SUPERVISOR, actual);
    }
}
