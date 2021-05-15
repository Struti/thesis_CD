package com.me.thesis.holiday.service.child;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.child.dao.ChildDao;
import com.me.thesis.holiday.dal.child.transformer.facade.ChildEntityTransformerFacade;
import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.service.lib.CurrentDateProvider;
import com.me.thesis.holiday.service.person.PersonService;

/**
 * The type Child service.
 */
@Service
public class ChildService {

    @Autowired
    private ChildEntityTransformerFacade transformer;

    @Autowired
    private PersonService personService;

    @Autowired
    private ChildDao dao;

    @Autowired
    private CurrentDateProvider currentDateProvider;

    /**
     * Gets all children by person id.
     *
     * @param personId the person id
     *
     * @return the all children by person id
     */
    public List<ChildDetails> getAllChildrenByPersonId(Long personId) {
        return transformer.transformFullEntityListToDomainList(dao.findAllChildrenByPersonId(personId));
    }

    /**
     * Save child.
     *
     * @param childDetails the child details
     * @param personId     the person id
     */
    public void saveChild(ChildDetails childDetails, Long personId) {
        dao.saveChild(transformer.transformChildDomainToEntity(childDetails, personService.getPersonById(personId)));
    }

    /**
     * Delete child.
     *
     * @param childId the child id
     */
    public void deleteChild(Long childId) {
        dao.deleteChild(childId);
    }

    /**
     * Gets child age.
     *
     * @param childBirthDate the child birth date
     *
     * @return the child age
     */
    public int getChildAge(LocalDate childBirthDate) {
        return Math.subtractExact(
            currentDateProvider.provide()
                .getYear(),
            childBirthDate.getYear()
        );
    }

}
