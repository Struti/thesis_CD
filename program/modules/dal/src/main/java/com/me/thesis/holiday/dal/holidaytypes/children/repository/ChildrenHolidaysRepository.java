package com.me.thesis.holiday.dal.holidaytypes.children.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.holidaytypes.children.domain.ChildrenHolidaysEntity;

/**
 * The interface Children holidays repository.
 */
@Repository
public interface ChildrenHolidaysRepository extends JpaRepository<ChildrenHolidaysEntity, Long> {

}
