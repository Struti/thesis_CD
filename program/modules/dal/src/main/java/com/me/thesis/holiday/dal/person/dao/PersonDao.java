package com.me.thesis.holiday.dal.person.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.repository.PersonRepository;

/**
 * The type Person dao.
 */
@Component
public class PersonDao {

    @Autowired
    private PersonRepository repository;

    /**
     * Save person person entity.
     *
     * @param personEntity the person entity
     *
     * @return the person entity
     */
    public PersonEntity savePerson(PersonEntity personEntity) {
        repository.save(personEntity);
        return findById(personEntity.getPersonId());
    }

    /**
     * Find by id person entity.
     *
     * @param personId the person id
     *
     * @return the person entity
     */
    public PersonEntity findById(Long personId) {
        return repository.findById(personId)
            .orElseThrow(NoSuchElementException::new);
    }

    /**
     * Find all person details list.
     *
     * @return the list
     */
    public List<PersonEntity> findAllPersonDetails() {
        return repository.findAll();
    }

    /**
     * Delete person person entity.
     *
     * @param personId the person id
     *
     * @return the person entity
     */
    public PersonEntity deletePerson(Long personId) {
        PersonEntity entity = repository.findById(personId)
            .orElseThrow(NoSuchElementException::new);
        repository.delete(entity);
        return entity;
    }

}
