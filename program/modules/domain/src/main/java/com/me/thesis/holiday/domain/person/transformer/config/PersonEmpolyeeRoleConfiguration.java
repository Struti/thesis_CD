package com.me.thesis.holiday.domain.person.transformer.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.me.thesis.holiday.domain.person.enums.PersonDomainEmployeeRole;
import com.me.thesis.holiday.dto.person.enums.PersonDtoEmployeeRole;

/**
 * The type Person empolyee role configuration.
 */
@Configuration
public class PersonEmpolyeeRoleConfiguration {

    /**
     * Person employee role map map.
     *
     * @return the map
     */
    @Bean
    public Map<PersonDtoEmployeeRole, PersonDomainEmployeeRole> personEmployeeRoleMap() {
        return Map.ofEntries(
            Map.entry(PersonDtoEmployeeRole.EMPLOYEE, PersonDomainEmployeeRole.EMPLOYEE),
            Map.entry(PersonDtoEmployeeRole.SUPERVISOR, PersonDomainEmployeeRole.SUPERVISOR)
        );
    }

}
