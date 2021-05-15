package com.me.thesis.holiday.dal.person.transformer.enums;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.person.enums.PersonEntityEmployeeRole;
import com.me.thesis.holiday.domain.person.enums.PersonDomainEmployeeRole;

/**
 * The type Person entity employee role transformer.
 */
@Component
public class PersonEntityEmployeeRoleTransformer {

    @Autowired
    private Map<PersonEntityEmployeeRole, PersonDomainEmployeeRole> personEmployeeRoleEntityToDomainMap;

    @Autowired
    private Map<PersonDomainEmployeeRole, PersonEntityEmployeeRole> personEmployeeRoleDomainToEntityMap;

    /**
     * Transform person entity employee role.
     *
     * @param employeeRole the employee role
     *
     * @return the person entity employee role
     */
    public PersonEntityEmployeeRole transform(PersonDomainEmployeeRole employeeRole) {
        return personEmployeeRoleDomainToEntityMap.getOrDefault(employeeRole, PersonEntityEmployeeRole.EMPLOYEE);
    }

    /**
     * Transform person domain employee role.
     *
     * @param employeeRole the employee role
     *
     * @return the person domain employee role
     */
    public PersonDomainEmployeeRole transform(PersonEntityEmployeeRole employeeRole) {
        return personEmployeeRoleEntityToDomainMap.getOrDefault(employeeRole, PersonDomainEmployeeRole.EMPLOYEE);
    }

}
