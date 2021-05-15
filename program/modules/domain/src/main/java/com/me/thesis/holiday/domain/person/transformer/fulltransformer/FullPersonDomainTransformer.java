package com.me.thesis.holiday.domain.person.transformer.fulltransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.transformer.enums.PersonDomainEmployeeRoleTransformer;
import com.me.thesis.holiday.domain.person.transformer.enums.PersonDomainGenderTransformer;
import com.me.thesis.holiday.domain.person.transformer.enums.PersonDomainTitleTransformer;
import com.me.thesis.holiday.dto.person.domain.PersonDetailsDto;

/**
 * The type Full person domain transformer.
 */
@Component
public class FullPersonDomainTransformer {

    @Autowired
    private PersonDomainTitleTransformer titleTransformer;

    @Autowired
    private PersonDomainGenderTransformer genderTransformer;

    @Autowired
    private PersonDomainEmployeeRoleTransformer employeeRoleTransformer;

    /**
     * Transform person details.
     *
     * @param dto the dto
     *
     * @return the person details
     */
    public PersonDetails transform(PersonDetailsDto dto) {
        return PersonDetails.builder()
            .id(dto.getId())
            .title(titleTransformer.transform(dto.getTitle()))
            .fullName(dto.getFullName())
            .birthName(dto.getBirthName())
            .gender(genderTransformer.transform(dto.getGender()))
            .birthDate(dto.getBirthDate())
            .disability(dto.isDisability())
            .child(dto.isChild())
            .startDate(dto.getStartDate())
            .lastDay(dto.getLastDay()
                .orElse(null))
            .employeeRole(employeeRoleTransformer.transform(dto.getEmployeeRole()))
            .actualLocation(dto.getActualLocation())
            .build();
    }

}
