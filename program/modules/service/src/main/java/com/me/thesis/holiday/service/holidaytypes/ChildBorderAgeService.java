package com.me.thesis.holiday.service.holidaytypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.holidaytypes.children.dao.ChildBorderAgeDao;
import com.me.thesis.holiday.dal.holidaytypes.children.transformer.entitytransformer.ChildBorderAgeEntityTransformer;
import com.me.thesis.holiday.domain.holidaytypes.children.domain.ChildBorderAge;

/**
 * The type Child border age service.
 */
@Service
public class ChildBorderAgeService {

    @Autowired
    private ChildBorderAgeDao dao;

    @Autowired
    private ChildBorderAgeEntityTransformer transformer;

    /**
     * Gets age.
     *
     * @return the age
     */
    public Integer getAge() {
        return dao.getChildBorderAge();
    }

    /**
     * Gets child border age.
     *
     * @return the child border age
     */
    public ChildBorderAge getChildBorderAge() {
        return transformer.transform(dao.getChildBorderAgeEntity());
    }

    /**
     * Save child border age.
     *
     * @param childBorderAge the child border age
     */
    public void saveChildBorderAge(ChildBorderAge childBorderAge) {
        dao.saveChildBorderAge(transformer.transform(childBorderAge));
    }

    /**
     * Delete child border age.
     *
     * @param id the id
     */
    public void deleteChildBorderAge(Long id) {
        dao.deleteChildBorderAge(id);
    }

}
