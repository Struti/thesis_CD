package com.me.thesis.holiday.dal.child.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.child.domain.ChildEntity;
import com.me.thesis.holiday.dal.child.repository.ChildRepository;

/**
 * The type Child dao.
 */
@Service
public class ChildDao {

    @Autowired
    private ChildRepository repository;

    /**
     * Find all children by person id list.
     *
     * @param personId the person id
     *
     * @return the list
     */
    public List<ChildEntity> findAllChildrenByPersonId(Long personId) {
        return repository.findAllByPerson_PersonId(personId);
    }

    /**
     * Save child.
     *
     * @param childEntity the child entity
     */
    public void saveChild(ChildEntity childEntity) {
        repository.save(childEntity);
    }

    /**
     * Delete child.
     *
     * @param childId the child id
     */
    public void deleteChild(Long childId) {
        repository.deleteById(childId);
    }

}
