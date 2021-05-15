package com.me.thesis.holiday.dal.holidaytypes.children.domain;

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

/**
 * The type Child border age entity.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "child_border_age")
public class ChildBorderAgeEntity {

    @Id
    @Column(name = "child_border_age_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childAgeBorderId;
    @Column(name = "border_age")
    private int borderAge;

}
