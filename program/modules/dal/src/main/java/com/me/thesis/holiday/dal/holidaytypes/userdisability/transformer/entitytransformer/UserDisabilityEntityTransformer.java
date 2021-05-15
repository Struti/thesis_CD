package com.me.thesis.holiday.dal.holidaytypes.userdisability.transformer.entitytransformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.userdisability.domain.UserDisabilityHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.userdisability.domain.UserDisabilityHolidays;

/**
 * The type User disability entity transformer.
 */
@Component
public class UserDisabilityEntityTransformer {

    /**
     * Transform list.
     *
     * @param entities the entities
     *
     * @return the list
     */
    public List<UserDisabilityHolidays> transform(List<UserDisabilityHolidaysEntity> entities) {
        return entities.stream()
            .map(this::transform)
            .collect(Collectors.toList());
    }

    private UserDisabilityHolidays transform(UserDisabilityHolidaysEntity entity) {
        return UserDisabilityHolidays.builder()
            .id(entity.getId())
            .type(entity.getType())
            .description(entity.getDescription())
            .day(entity.getDay())
            .build();
    }

    /**
     * Transform user disability holidays entity.
     *
     * @param domain the domain
     *
     * @return the user disability holidays entity
     */
    public UserDisabilityHolidaysEntity transform(UserDisabilityHolidays domain) {
        return UserDisabilityHolidaysEntity.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .day(domain.getDay())
            .build();
    }

}
