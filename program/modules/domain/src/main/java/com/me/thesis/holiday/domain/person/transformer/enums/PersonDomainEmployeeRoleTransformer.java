package com.me.thesis.holiday.domain.person.transformer.enums;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.person.enums.PersonDomainEmployeeRole;
import com.me.thesis.holiday.dto.person.enums.PersonDtoEmployeeRole;

/**
 * The type Person domain employee role transformer.
 */
@Component
public class PersonDomainEmployeeRoleTransformer {

    @Autowired
    private Map<PersonDtoEmployeeRole, PersonDomainEmployeeRole> personEmployeeRoleMap;

    /**
     * Transform person domain employee role.
     *
     * @param employeeRole the employee role
     *
     * @return the person domain employee role
     */
    public PersonDomainEmployeeRole transform(PersonDtoEmployeeRole employeeRole) {
        return personEmployeeRoleMap.get(employeeRole);
    }

}
