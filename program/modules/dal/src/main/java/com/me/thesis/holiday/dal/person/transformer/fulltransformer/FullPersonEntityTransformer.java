package com.me.thesis.holiday.dal.person.transformer.fulltransformer;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidayplan.domain.HolidayCalculationPlanEntity;
import com.me.thesis.holiday.dal.holidayplan.transfromer.HolidayCalculationPlanEntityTransformer;
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.transformer.enums.PersonEntityEmployeeRoleTransformer;
import com.me.thesis.holiday.dal.person.transformer.enums.PersonEntityGenderTransformer;
import com.me.thesis.holiday.dal.person.transformer.enums.PersonEntityTitleTransformer;
import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;

/**
 * The type Full person entity transformer.
 */
@Component
public class FullPersonEntityTransformer {

    @Autowired
    private PersonEntityEmployeeRoleTransformer roleTransformer;

    @Autowired
    private PersonEntityGenderTransformer genderTransformer;

    @Autowired
    private PersonEntityTitleTransformer titleTransformer;

    @Autowired
    private HolidayCalculationPlanEntityTransformer holidayCalculationPlanEntityTransformer;

    @Autowired
    private SpecialPlanTransformer specialPlanTransformer;

    /**
     * Transform set.
     *
     * @param personEntities the person entities
     *
     * @return the set
     */
    public Set<PersonDetails> transform(List<PersonEntity> personEntities) {
        return personEntities.stream()
            .map(this::transform)
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * Transform person details.
     *
     * @param entity the entity
     *
     * @return the person details
     */
    public PersonDetails transform(PersonEntity entity) {
        return PersonDetails.builder()
            .id(entity.getPersonId())
            .title(titleTransformer.transform(entity.getTitle()))
            .fullName(entity.getFullName())
            .birthName(entity.getBirthName())
            .gender(genderTransformer.transform(entity.getGender()))
            .birthDate(entity.getBirthDate())
            .disability(entity.isDisability())
            .child(entity.isChild())
            .startDate(entity.getStartDate())
            .lastDay(entity.getLastDay())
            .employeeRole(roleTransformer.transform(entity.getEmployeeRole()))
            .actualLocation(entity.getActualLocation())
            .calculationPlan(holidayCalculationPlanEntityTransformer.transform(entity.getHolidayCalculationPlanEntity()))
            .specificPlan(specialPlanTransformer.transform(entity.getSpecificPlan()))
            .build();
    }

    /**
     * Transform person entity.
     *
     * @param personDetails   the person details
     * @param calculationPlan the calculation plan
     *
     * @return the person entity
     */
    public PersonEntity transform(PersonDetails personDetails, HolidayCalculationPlan calculationPlan) {
        PersonEntity personEntity = transform(personDetails);
        personEntity.setHolidayCalculationPlanEntity(holidayCalculationPlanEntityTransformer.transform(calculationPlan));
        return personEntity;
    }

    /**
     * Transform person entity.
     *
     * @param personDetails the person details
     *
     * @return the person entity
     */
    public PersonEntity transform(PersonDetails personDetails) {
        return PersonEntity.builder()
            .personId(personDetails.getId())
            .title(titleTransformer.transform(personDetails.getTitle()))
            .fullName(personDetails.getFullName())
            .birthName(personDetails.getBirthName())
            .gender(genderTransformer.transform(personDetails.getGender()))
            .birthDate(personDetails.getBirthDate())
            .disability(personDetails.isDisability())
            .child(personDetails.isChild())
            .startDate(personDetails.getStartDate())
            .lastDay(personDetails.getLastDay()
                .orElse(null))
            .employeeRole(roleTransformer.transform(personDetails.getEmployeeRole()))
            .actualLocation(personDetails.getActualLocation())
            .holidayCalculationPlanEntity(getPlanEntity(personDetails))
            .specificPlan(getSpecificPlanEntity(personDetails))
            .build();
    }

    private HolidayCalculationPlanEntity getPlanEntity(PersonDetails personDetails) {
        return personDetails.getCalculationPlan()
            .map(holidayCalculationPlanEntityTransformer::transform)
            .orElse(null);
    }

    private String getSpecificPlanEntity(PersonDetails personDetails) {
        return personDetails.getSpecificPlan()
            .map(specialPlanTransformer::transform)
            .orElse(null);
    }

    /**
     * Transform person entity.
     *
     * @param personDetails the person details
     * @param specificPlan  the specific plan
     *
     * @return the person entity
     */
    public PersonEntity transform(PersonDetails personDetails, SpecificPlan specificPlan) {
        PersonEntity personEntity = transform(personDetails);
        personEntity.setSpecificPlan(specialPlanTransformer.transform(specificPlan));
        return personEntity;
    }

}
