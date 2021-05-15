package com.me.thesis.holiday.dal.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.person.domain.PersonEntity;

/**
 * The interface Person repository.
 */
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

}
