package com.me.thesis.holiday.dal.holidayhistory.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.me.thesis.holiday.dal.person.domain.PersonEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The entity type Holiday history.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "holiday_history")
public class HolidayHistoryEntity {

    @Id
    @Column(name = "holiday_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holidayHistoryId;
    @OneToOne(mappedBy = "holidayHistoryEntity")
    private PersonEntity personEntity;
    @Column(name = "history")
    private String history;
}
