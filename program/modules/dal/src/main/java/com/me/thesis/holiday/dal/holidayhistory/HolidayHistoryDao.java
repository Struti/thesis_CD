package com.me.thesis.holiday.dal.holidayhistory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidayhistory.domain.HolidayHistoryEntity;
import com.me.thesis.holiday.dal.holidayhistory.repository.HolidayHistoryRepository;
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.enums.PersonEntityGender;

/**
 * The type Holiday history dao.
 */
@Component
public class HolidayHistoryDao {

    @Autowired
    private HolidayHistoryRepository repository;

    /**
     * Save.
     *
     * @param holidayHistoryEntity the holiday history entity
     */
    public void save(HolidayHistoryEntity holidayHistoryEntity) {
        repository.save(holidayHistoryEntity);
    }

    /**
     * Gets history.
     *
     * @param id the id
     *
     * @return the history
     */
    public HolidayHistoryEntity getHistory(Long id) {
        return repository.getOne(id);
    }

    /**
     * Gets dads histories.
     *
     * @return the dads histories
     */
    public List<HolidayHistoryEntity> getDadsHistories() {
        return repository.findAllByPersonEntity_Gender(PersonEntityGender.MALE)
            .stream()
            .map(HolidayHistoryEntity::getPersonEntity)
            .filter(PersonEntity::isChild)
            .map(PersonEntity::getHolidayHistoryEntity)
            .collect(Collectors.toList());
    }

    /**
     * Gets history by person id.
     *
     * @param personId the person id
     *
     * @return the history by person id
     */
    public HolidayHistoryEntity getHistoryByPersonId(Long personId) {
        return repository.findByPersonEntity_PersonId(personId);
    }

}
