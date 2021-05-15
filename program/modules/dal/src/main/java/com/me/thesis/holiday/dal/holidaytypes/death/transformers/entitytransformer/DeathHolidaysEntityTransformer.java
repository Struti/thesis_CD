package com.me.thesis.holiday.dal.holidaytypes.death.transformers.entitytransformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.death.domain.DeathHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.death.domain.DeathHolidays;

/**
 * The type Death holidays entity transformer.
 */
@Component
public class DeathHolidaysEntityTransformer {

    /**
     * Transform list.
     *
     * @param entities the entities
     *
     * @return the list
     */
    public List<DeathHolidays> transform(List<DeathHolidaysEntity> entities) {
        return entities.stream()
            .map(this::transform)
            .collect(Collectors.toList());
    }

    /**
     * Transform death holidays.
     *
     * @param entity the entity
     *
     * @return the death holidays
     */
    public DeathHolidays transform(DeathHolidaysEntity entity) {
        return DeathHolidays.builder()
            .id(entity.getId())
            .type(entity.getType())
            .description(entity.getDescription())
            .day(entity.getDay())
            .build();
    }

    /**
     * Transform death holidays entity.
     *
     * @param domain the domain
     *
     * @return the death holidays entity
     */
    public DeathHolidaysEntity transform(DeathHolidays domain) {
        return DeathHolidaysEntity.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .day(domain.getDay())
            .build();
    }

}
