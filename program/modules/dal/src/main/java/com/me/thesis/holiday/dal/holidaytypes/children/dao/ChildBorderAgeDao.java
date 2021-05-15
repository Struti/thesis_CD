package com.me.thesis.holiday.dal.holidaytypes.children.dao;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.children.domain.ChildBorderAgeEntity;
import com.me.thesis.holiday.dal.holidaytypes.children.repository.ChildBorderAgeRepository;

/**
 * The type Child border age dao.
 */
@Component
public class ChildBorderAgeDao {

    @Autowired
    private ChildBorderAgeRepository repository;

    /**
     * Save child border age.
     *
     * @param childBorderAge the child border age
     */
    public void saveChildBorderAge(ChildBorderAgeEntity childBorderAge) {
        repository.save(childBorderAge);
    }

    /**
     * Delete child border age.
     *
     * @param id the id
     */
    public void deleteChildBorderAge(Long id) {
        repository.deleteById(id);
    }

    /**
     * Gets child border age.
     *
     * @return the child border age
     */
    public Integer getChildBorderAge() {
        return repository.findAll()
            .stream()
            .map(ChildBorderAgeEntity::getBorderAge)
            .collect(Collectors.toList())
            .stream()
            .findFirst()
            .orElse(null);
    }

    /**
     * Gets child border age entity.
     *
     * @return the child border age entity
     */
    public ChildBorderAgeEntity getChildBorderAgeEntity() {
        return repository.findAll()
            .stream()
            .findFirst()
            .orElse(null);
    }

}
