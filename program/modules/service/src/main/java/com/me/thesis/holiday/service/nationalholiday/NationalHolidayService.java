package com.me.thesis.holiday.service.nationalholiday;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.me.thesis.holiday.dal.nationalholiday.dao.NationalHolidayDao;
import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;

/**
 * The type National holiday service.
 */
@Service
public class NationalHolidayService {

    private static final int YEARS_TO_ADD = 1;
    private static final long TEN_YEAR = 10L;

    @Autowired
    private NationalHolidayDao dao;

    /**
     * Gets national holidays.
     *
     * @return the national holidays
     */
    public List<NationalHoliday> getNationalHolidays() {
        return dao.getNationalHolidays();
    }

    /**
     * Save national holiday.
     *
     * @param nationalHoliday the national holiday
     */
    public void saveNationalHoliday(NationalHoliday nationalHoliday) {
        dao.saveNationalHoliday(nationalHoliday);
        addFixForNextTenYear();
    }

    public void addFixForNextTenYear() {
        Set<NationalHoliday> holidaysWithoutDuplication = getHolidaysWithoutDuplication(getStoredNationalHolidayDates());
        saveNationalHolidays(Lists.newArrayList(holidaysWithoutDuplication));
    }

    private Set<NationalHoliday> getHolidaysWithoutDuplication(List<LocalDate> storedNationalHolidayDates) {
        return storedNationalHolidayDates.stream()
            .map(this::getNextTenYearFixHolidays)
            .flatMap(Collection::stream)
            .filter(date -> isDuplicated(storedNationalHolidayDates, date))
            .map(this::createFixNationalHoliday)
            .collect(Collectors.toSet());

    }

    private List<LocalDate> getStoredNationalHolidayDates() {
        return getNationalHolidays().stream()
            .filter(NationalHoliday::isFix)
            .map(NationalHoliday::getDate)
            .collect(Collectors.toList());
    }

    /**
     * Save national holidays.
     *
     * @param nationalHolidays the national holidays
     */
    public void saveNationalHolidays(List<NationalHoliday> nationalHolidays) {
        dao.saveNationalHolidays(nationalHolidays);
    }

    private List<LocalDate> getNextTenYearFixHolidays(LocalDate nationalHoliday) {
        return Stream.iterate(nationalHoliday, date -> date.plusYears(YEARS_TO_ADD))
            .limit(TEN_YEAR)
            .collect(Collectors.toList());
    }

    private boolean isDuplicated(List<LocalDate> storedNationalHolidayDates, LocalDate nationalHoliday) {
        return !storedNationalHolidayDates.contains(nationalHoliday);
    }

    private NationalHoliday createFixNationalHoliday(LocalDate date) {
        return NationalHoliday.builder()
            .date(date)
            .fix(true)
            .build();
    }

    /**
     * Delete national holiday.
     *
     * @param id the id
     */
    public void deleteNationalHoliday(Long id) {
        dao.deleteNationalHoliday(id);
    }

    /**
     * Delete earlier national holidays.
     *
     * @param date the date
     */
    public void deleteEarlierNationalHolidays(LocalDate date) {
        dao.deleteEarlierNationalHolidays(date);
    }

}
