package com.me.thesis.holiday.domain.person.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.domain.holidaytypes.age.domain.AgeBasedHolidays;
import com.me.thesis.holiday.domain.holidaytypes.age.transformer.AgeHolidaysTransformer;
import com.me.thesis.holiday.domain.holidaytypes.basic.domain.BasicHolidays;
import com.me.thesis.holiday.domain.holidaytypes.basic.transformer.BasicHolidaysTransformer;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.transformer.ChildDisabilityHolidaysTransformer;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildrenHolidays;
import com.me.thesis.holiday.domain.holidaytypes.children.transformer.ChildrenHolidaysTransformer;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.transformer.UserDisabilityHolidaysTransformer;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;
import com.me.thesis.holiday.dto.holidaytypes.AgeHolidayDetailsDto;
import com.me.thesis.holiday.dto.holidaytypes.BasicHolidayDetailsDto;
import com.me.thesis.holiday.dto.holidaytypes.ChildDisabilityHolidayDetailsDto;
import com.me.thesis.holiday.dto.holidaytypes.ChildrenHolidayDetailsDto;
import com.me.thesis.holiday.dto.holidaytypes.UserDisabilityHolidayDetailsDto;
import com.me.thesis.holiday.dto.person.domain.SpecificPlanDto;

/**
 * The type Specific plan factory.
 */
@Component
public class SpecificPlanFactory {

    @Autowired
    private BasicHolidaysTransformer basicHolidaysTransformer;
    @Autowired
    private AgeHolidaysTransformer ageHolidaysTransformer;
    @Autowired
    private UserDisabilityHolidaysTransformer userDisabilityHolidaysTransformer;
    @Autowired
    private ChildrenHolidaysTransformer childrenHolidaysTransformer;
    @Autowired
    private ChildDisabilityHolidaysTransformer childDisabilityHolidaysTransformer;

    /**
     * Transform specific plan.
     *
     * @param specificPlanDto the specific plan dto
     *
     * @return the specific plan
     */
    public SpecificPlan transform(SpecificPlanDto specificPlanDto) {
        SpecificPlan.SpecificPlanBuilder specificPlan = SpecificPlan.builder();
        specificPlan.basicHolidays(doBasicHolidaysTransformation(specificPlanDto.getBasicHolidays()));
        specificPlanDto.getAgeBasedHolidays()
            .ifPresent(ageHolidayDetailsDto -> specificPlan.ageBasedHolidays(doAgeBasedHolidaysTransformation(ageHolidayDetailsDto)));
        specificPlanDto.getUserDisabilityHolidays()
            .ifPresent(userDisabilityHolidayDetailsDto -> specificPlan.userDisabilityHolidays(doUserDisabilityHolidaysTransformation(userDisabilityHolidayDetailsDto)));
        specificPlanDto.getChildrenHolidays()
            .ifPresent(childDetailsDto -> specificPlan.childrenHolidays(doChildrenHolidaysTransformation(childDetailsDto)));
        specificPlanDto.getChildDisabilityHolidays()
            .ifPresent(dto -> specificPlan.childDisabilityHolidays(doChildDisabilityHolidaysTransformation(dto)));
        return specificPlan.build();
    }

    private BasicHolidays doBasicHolidaysTransformation(BasicHolidayDetailsDto basicHolidayDetailsDto) {
        return basicHolidaysTransformer.transform(basicHolidayDetailsDto);
    }

    private AgeBasedHolidays doAgeBasedHolidaysTransformation(AgeHolidayDetailsDto ageHolidayDetailsDto) {
        return ageHolidaysTransformer.transform(ageHolidayDetailsDto);
    }

    private UserDisabilityHolidays doUserDisabilityHolidaysTransformation(UserDisabilityHolidayDetailsDto userDisabilityHolidayDetailsDto) {
        return userDisabilityHolidaysTransformer.transform(userDisabilityHolidayDetailsDto);
    }

    private ChildrenHolidays doChildrenHolidaysTransformation(ChildrenHolidayDetailsDto childrenHolidayDetailsDto) {
        return childrenHolidaysTransformer.transform(childrenHolidayDetailsDto);
    }

    private ChildDisabilityHolidays doChildDisabilityHolidaysTransformation(ChildDisabilityHolidayDetailsDto childDisabilityHolidayDetailsDto) {
        return childDisabilityHolidaysTransformer.transform(childDisabilityHolidayDetailsDto);
    }

}
