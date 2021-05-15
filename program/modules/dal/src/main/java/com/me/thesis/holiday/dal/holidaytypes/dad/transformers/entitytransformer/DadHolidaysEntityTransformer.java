package com.me.thesis.holiday.dal.holidaytypes.dad.transformers.entitytransformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.dad.domain.DadHolidaysEntity;
import com.me.thesis.holiday.domain.holidaytypes.dad.domain.DadHolidays;

/**
 * The type Dad holidays entity transformer.
 */
@Component
public class DadHolidaysEntityTransformer {

    /**
     * Transform list.
     *
     * @param entities the entities
     *
     * @return the list
     */
    public List<DadHolidays> transform(List<DadHolidaysEntity> entities) {
        return entities.stream()
            .map(this::transform)
            .collect(Collectors.toList());
    }

    private DadHolidays transform(DadHolidaysEntity entity) {
        return DadHolidays.builder()
            .id(entity.getId())
            .type(entity.getType())
            .description(entity.getDescription())
            .limit(entity.getLimit())
            .day(entity.getDay())
            .build();
    }

    /**
     * Transform dad holidays entity.
     *
     * @param domain the domain
     *
     * @return the dad holidays entity
     */
    public DadHolidaysEntity transform(DadHolidays domain) {
        return DadHolidaysEntity.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .limit(domain.getLimit())
            .day(domain.getDay())
            .build();
    }

}
