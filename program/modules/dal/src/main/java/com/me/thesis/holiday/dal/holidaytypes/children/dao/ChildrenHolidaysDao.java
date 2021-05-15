package com.me.thesis.holiday.dal.holidaytypes.children.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidaytypes.children.domain.ChildrenHolidaysEntity;
import com.me.thesis.holiday.dal.holidaytypes.children.repository.ChildrenHolidaysRepository;

/**
 * The type Children holidays dao.
 */
@Component
public class ChildrenHolidaysDao {

    @Autowired
    private ChildrenHolidaysRepository repository;

    /**
     * Gets all children.
     *
     * @return the all children
     */
    public List<ChildrenHolidaysEntity> getAllChildren() {
        return repository.findAll();
    }

    /**
     * Save children.
     *
     * @param holiday the holiday
     */
    public void saveChildren(ChildrenHolidaysEntity holiday) {
        repository.save(holiday);
    }

    /**
     * Delete children.
     *
     * @param childId the child id
     */
    public void deleteChildren(Long childId) {
        repository.deleteById(childId);
    }

}
