package com.me.thesis.holiday.dal.holidaytypes.maternity.transformers.entitytransformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.maternity.domain.MaternityHolidayEntity;
import com.me.thesis.holiday.domain.holidaytypes.maternity.domain.MaternityHoliday;

/**
 * The type Maternity holiday entity transformer.
 */
@Component
public class MaternityHolidayEntityTransformer {

    /**
     * Transform list.
     *
     * @param entities the entities
     *
     * @return the list
     */
    public List<MaternityHoliday> transform(List<MaternityHolidayEntity> entities) {
        return entities.stream()
            .map(this::transform)
            .collect(Collectors.toList());
    }

    private MaternityHoliday transform(MaternityHolidayEntity entity) {
        return MaternityHoliday.builder()
            .id(entity.getId())
            .type(entity.getType())
            .description(entity.getDescription())
            .beforeDays(entity.getBeforeDays())
            .afterDays(entity.getAfterDays())
            .build();
    }

    /**
     * Transform maternity holiday entity.
     *
     * @param domain the domain
     *
     * @return the maternity holiday entity
     */
    public MaternityHolidayEntity transform(MaternityHoliday domain) {
        return MaternityHolidayEntity.builder()
            .id(domain.getId()
                .orElse(null))
            .type(domain.getType())
            .description(domain.getDescription())
            .beforeDays(domain.getBeforeDays())
            .afterDays(domain.getAfterDays())
            .build();
    }

}
