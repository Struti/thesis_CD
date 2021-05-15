package com.me.thesis.holiday.dal.holidaytypes.childdisability.transformer.entitytransformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.childdisability.domain.ChildDisabilityHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.childdisability.domain.ChildDisabilityHolidays;

/**
 * The type Child disability holidays entity transformer.
 */
@Component
public class ChildDisabilityHolidaysEntityTransformer {

    /**
     * Transform list.
     *
     * @param entities the entities
     *
     * @return the list
     */
    public List<ChildDisabilityHolidays> transform(List<ChildDisabilityHolidaysEntity> entities) {
        return entities.stream()
            .map(this::transform)
            .collect(Collectors.toList());
    }

    private ChildDisabilityHolidays transform(ChildDisabilityHolidaysEntity entity) {
        return ChildDisabilityHolidays.builder()
            .id(entity.getId())
            .type(entity.getType())
            .description(entity.getDescription())
            .childrenNumber(entity.getChildrenNumber())
            .day(entity.getDay())
            .build();
    }

    /**
     * Transform child disability holidays entity.
     *
     * @param domain the domain
     *
     * @return the child disability holidays entity
     */
    public ChildDisabilityHolidaysEntity transform(ChildDisabilityHolidays domain) {
        return ChildDisabilityHolidaysEntity.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .childrenNumber(domain.getChildrenNumber())
            .day(domain.getDay())
            .build();
    }

}
