package com.me.thesis.holiday.dal.child.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.thesis.holiday.dal.child.domain.ChildEntity;

/**
 * The interface Child repository.
 */
@Repository
public interface ChildRepository extends JpaRepository<ChildEntity, Long> {

    List<ChildEntity> findAllByPerson_PersonId(Long personId);

}
