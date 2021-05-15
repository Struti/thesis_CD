package com.me.thesis.holiday.domain.person.transformer.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.me.thesis.holiday.domain.person.enums.PersonDomainGender;
import com.me.thesis.holiday.dto.person.enums.PersonDtoGender;

/**
 * The type Person gender configuration.
 */
@Configuration
public class PersonGenderConfiguration {

    /**
     * Person gender map map.
     *
     * @return the map
     */
    @Bean
    public Map<PersonDtoGender, PersonDomainGender> personGenderMap() {
        return Map.ofEntries(
            Map.entry(PersonDtoGender.MALE, PersonDomainGender.MALE),
            Map.entry(PersonDtoGender.FEMALE, PersonDomainGender.FEMALE)
        );
    }

}
