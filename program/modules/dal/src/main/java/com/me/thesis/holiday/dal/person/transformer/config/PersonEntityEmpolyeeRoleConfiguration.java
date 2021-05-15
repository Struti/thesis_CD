package com.me.thesis.holiday.dal.person.transformer.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.me.thesis.holiday.dal.person.enums.PersonEntityEmployeeRole;
import com.me.thesis.holiday.domain.person.enums.PersonDomainEmployeeRole;

/**
 * The type Person entity empolyee role configuration.
 */
@Configuration
public class PersonEntityEmpolyeeRoleConfiguration {

    /**
     * Person employee role entity to domain map map.
     *
     * @return the map
     */
    @Bean
    public Map<PersonEntityEmployeeRole, PersonDomainEmployeeRole> personEmployeeRoleEntityToDomainMap() {
        return Map.ofEntries(
            Map.entry(PersonEntityEmployeeRole.EMPLOYEE, PersonDomainEmployeeRole.EMPLOYEE),
            Map.entry(PersonEntityEmployeeRole.SUPERVISOR, PersonDomainEmployeeRole.SUPERVISOR)
        );
    }

    /**
     * Person employee role domain to entity map map.
     *
     * @return the map
     */
    @Bean
    public Map<PersonDomainEmployeeRole, PersonEntityEmployeeRole> personEmployeeRoleDomainToEntityMap() {
        return Map.ofEntries(
            Map.entry(PersonDomainEmployeeRole.EMPLOYEE, PersonEntityEmployeeRole.EMPLOYEE),
            Map.entry(PersonDomainEmployeeRole.SUPERVISOR, PersonEntityEmployeeRole.SUPERVISOR)
        );
    }

}
