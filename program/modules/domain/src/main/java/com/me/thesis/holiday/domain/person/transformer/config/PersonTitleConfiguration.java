package com.me.thesis.holiday.domain.person.transformer.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.me.thesis.holiday.domain.person.enums.PersonDomainTitle;
import com.me.thesis.holiday.dto.person.enums.PersonDtoTitle;

/**
 * The type Person title configuration.
 */
@Configuration
public class PersonTitleConfiguration {

    /**
     * Person title map map.
     *
     * @return the map
     */
    @Bean
    public Map<PersonDtoTitle, PersonDomainTitle> personTitleMap() {
        return Map.ofEntries(
            Map.entry(PersonDtoTitle.NONE, PersonDomainTitle.NONE),
            Map.entry(PersonDtoTitle.DR1, PersonDomainTitle.DR1),
            Map.entry(PersonDtoTitle.DR2, PersonDomainTitle.DR2),
            Map.entry(PersonDtoTitle.HABIL, PersonDomainTitle.HABIL),
            Map.entry(PersonDtoTitle.PHD, PersonDomainTitle.PHD),
            Map.entry(PersonDtoTitle.PROF, PersonDomainTitle.PROF)
        );
    }

}
