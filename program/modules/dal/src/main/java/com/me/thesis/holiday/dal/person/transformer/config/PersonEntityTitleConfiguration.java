package com.me.thesis.holiday.dal.person.transformer.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.me.thesis.holiday.dal.person.enums.PersonEntityTitle;
import com.me.thesis.holiday.domain.person.enums.PersonDomainTitle;

/**
 * The type Person entity title configuration.
 */
@Configuration
public class PersonEntityTitleConfiguration {

    /**
     * Person title entity to domain map map.
     *
     * @return the map
     */
    @Bean
    public Map<PersonEntityTitle, PersonDomainTitle> personTitleEntityToDomainMap() {
        return Map.ofEntries(
            Map.entry(PersonEntityTitle.NONE, PersonDomainTitle.NONE),
            Map.entry(PersonEntityTitle.DR1, PersonDomainTitle.DR1),
            Map.entry(PersonEntityTitle.DR2, PersonDomainTitle.DR2),
            Map.entry(PersonEntityTitle.HABIL, PersonDomainTitle.HABIL),
            Map.entry(PersonEntityTitle.PHD, PersonDomainTitle.PHD),
            Map.entry(PersonEntityTitle.PROF, PersonDomainTitle.PROF)
        );
    }

    /**
     * Person title domain to entity map map.
     *
     * @return the map
     */
    @Bean
    public Map<PersonDomainTitle, PersonEntityTitle> personTitleDomainToEntityMap() {
        return Map.ofEntries(
            Map.entry(PersonDomainTitle.NONE, PersonEntityTitle.NONE),
            Map.entry(PersonDomainTitle.DR1, PersonEntityTitle.DR1),
            Map.entry(PersonDomainTitle.DR2, PersonEntityTitle.DR2),
            Map.entry(PersonDomainTitle.HABIL, PersonEntityTitle.HABIL),
            Map.entry(PersonDomainTitle.PHD, PersonEntityTitle.PHD),
            Map.entry(PersonDomainTitle.PROF, PersonEntityTitle.PROF)
        );
    }

}
