package com.me.thesis.holiday.dal.holidayplan.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * The entity type Holiday calculation plan.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "holiday_calculation_plan")
public class HolidayCalculationPlanEntity {

    @Id
    @Column(name = "calculation_plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calculationPlanId;
    @NonNull
    @Column(name = "holiday_types", columnDefinition = "LONGTEXT")
    private String holidayTypes;
    @Column(name = "description")
    private String description;

}
