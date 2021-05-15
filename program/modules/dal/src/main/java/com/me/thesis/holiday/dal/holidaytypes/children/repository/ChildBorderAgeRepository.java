package com.me.thesis.holiday.dal.holidaytypes.children.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.holidaytypes.children.domain.ChildBorderAgeEntity;

/**
 * The interface Child border age repository.
 */
@Repository
public interface ChildBorderAgeRepository extends JpaRepository<ChildBorderAgeEntity, Long> {

}
