package com.me.thesis.holiday.dal.child.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.me.thesis.holiday.dal.person.domain.PersonEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The entity type Child.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "child")
public class ChildEntity {

    @Id
    @Column(name = "child_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childId;
    @Column(name = "born")
    private boolean born = false;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private PersonEntity person;
    @Column(name = "expected_date")
    private LocalDate expectedDate;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Builder.Default
    @Column(name = "disability")
    private boolean disability = false;
    @Column(name = "disability_cert_exp_date")
    private LocalDate disabilityCertExpirationDate;
}
