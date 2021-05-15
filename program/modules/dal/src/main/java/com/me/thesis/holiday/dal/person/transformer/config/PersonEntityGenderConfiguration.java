package com.me.thesis.holiday.dal.person.transformer.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.me.thesis.holiday.dal.person.enums.PersonEntityGender;
import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;

/**
 * The type Person entity gender configuration.
 */
@Configuration
public class PersonEntityGenderConfiguration {

    /**
     * Person gender entity to domain map map.
     *
     * @return the map
     */
    @Bean
    public Map<PersonEntityGender, PersonDomainGender> personGenderEntityToDomainMap() {
        return Map.ofEntries(
            Map.entry(PersonEntityGender.MALE, PersonDomainGender.MALE),
            Map.entry(PersonEntityGender.FEMALE, PersonDomainGender.FEMALE)
        );
    }

    /**
     * Person gender domain to entity map map.
     *
     * @return the map
     */
    @Bean
    public Map<PersonDomainGender, PersonEntityGender> personGenderDomainToEntityMap() {
        return Map.ofEntries(
            Map.entry(PersonDomainGender.MALE, PersonEntityGender.MALE),
            Map.entry(PersonDomainGender.FEMALE, PersonEntityGender.FEMALE)
        );
    }

}
