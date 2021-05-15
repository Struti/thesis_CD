package com.me.thesis.holiday.dal.person.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.me.thesis.holiday.dal.child.domain.ChildEntity;
import com.me.thesis.holiday.dal.holidayhistory.domain.HolidayHistoryEntity;
import com.me.thesis.holiday.dal.holidayplan.domain.HolidayCalculationPlanEntity;
import com.me.thesis.holiday.dal.person.enums.PersonEntityEmployeeRole;
import com.me.thesis.holiday.dal.person.enums.PersonEntityGender;
import com.me.thesis.holiday.dal.person.enums.PersonEntityTitle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The entity type Person.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class PersonEntity {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;
    @Builder.Default
    @Column(name = "title")
    @Enumerated(EnumType.STRING)
    private PersonEntityTitle title = PersonEntityTitle.NONE;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "birth_name")
    private String birthName;
    @Builder.Default
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private PersonEntityGender gender = PersonEntityGender.MALE;
    @Builder.Default
    @Column(name = "birth_Date")
    private LocalDate birthDate = LocalDate.EPOCH;
    @Builder.Default
    @Column(name = "disability")
    private boolean disability = false;
    @Builder.Default
    @Column(name = "have_child")
    private boolean child = false;
    @OneToMany(mappedBy = "person")
    private List<ChildEntity> childEntities;
    @Builder.Default
    @Column(name = "start_date")
    private LocalDate startDate = LocalDate.EPOCH;
    @Column(name = "last_date")
    private LocalDate lastDay;
    @Builder.Default
    @Column(name = "employee_role")
    @Enumerated(EnumType.STRING)
    private PersonEntityEmployeeRole employeeRole = PersonEntityEmployeeRole.EMPLOYEE;
    @Column(name = "actual_location")
    private String actualLocation;
    @ManyToOne
    @JoinColumn(name = "holiday_calculation_plan_id")
    private HolidayCalculationPlanEntity holidayCalculationPlanEntity;
    @Column(name = "specific_plan", columnDefinition = "LONGTEXT")
    private String specificPlan;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "holiday_history_id")
    private HolidayHistoryEntity holidayHistoryEntity;

}
