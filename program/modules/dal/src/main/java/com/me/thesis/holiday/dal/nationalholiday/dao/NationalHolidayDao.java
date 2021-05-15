package com.me.thesis.holiday.dal.nationalholiday.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.nationalholiday.repository.NationalHolidayRepository;
import com.me.thesis.holiday.dal.nationalholiday.transformer.NationalHolidayEntityTransformer;
import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;

/**
 * The type National holiday dao.
 */
@Component
public class NationalHolidayDao {

    @Autowired
    private NationalHolidayRepository repository;

    @Autowired
    private NationalHolidayEntityTransformer transformer;

    /**
     * Gets national holidays.
     *
     * @return the national holidays
     */
    public List<NationalHoliday> getNationalHolidays() {
        return repository.findAll()
            .stream()
            .map(transformer::transform)
            .collect(Collectors.toList());
    }

    /**
     * Delete national holidays.
     *
     * @param id the id
     */
    public void deleteNationalHoliday(Long id) {
        repository.deleteById(id);
    }

    /**
     * Delete earlier national holidays.
     *
     * @param date the date
     */
    public void deleteEarlierNationalHolidays(LocalDate date) {
        repository.deleteAllByDateBefore(date);
    }

    /**
     * Save national holidays.
     *
     * @param nationalHolidays the national holidays
     */
    public void saveNationalHolidays(List<NationalHoliday> nationalHolidays) {
        repository.saveAll(nationalHolidays.stream()
            .map(transformer::transform)
            .collect(Collectors.toList()));
    }

    /**
     * Save national holiday.
     *
     * @param nationalHoliday the national holiday
     */
    public void saveNationalHoliday(NationalHoliday nationalHoliday) {
        repository.save(transformer.transform(nationalHoliday));
    }

}
