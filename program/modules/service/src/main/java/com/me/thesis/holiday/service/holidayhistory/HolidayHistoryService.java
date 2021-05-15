package com.me.thesis.holiday.service.holidayhistory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.holidayhistory.HolidayHistoryDao;
import com.me.thesis.holiday.dal.holidayhistory.transformer.HolidayHistoryEntityTransformer;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;

/**
 * The type Holiday history service.
 */
@Service
public class HolidayHistoryService {

    @Autowired
    private HolidayHistoryDao dao;

    @Autowired
    private HolidayHistoryEntityTransformer transformer;

    /**
     * Save.
     *
     * @param holidayHistory the holiday history
     */
    public void save(HolidayHistory holidayHistory) {
        dao.save(transformer.transform(holidayHistory));
    }

    /**
     * Gets history.
     *
     * @param id the id
     *
     * @return the history
     */
    public HolidayHistory getHistory(Long id) {
        return transformer.transform(dao.getHistory(id));
    }

    /**
     * Gets dads histories.
     *
     * @return the dads histories
     */
    public List<HolidayHistory> getDadsHistories() {
        return dao.getDadsHistories()
            .stream()
            .map(transformer::transform)
            .collect(Collectors.toList());
    }

    /**
     * Gets history by person id.
     *
     * @param personId the person id
     *
     * @return the history by person id
     */
    public HolidayHistory getHistoryByPersonId(Long personId) {
        return transformer.transform(dao.getHistoryByPersonId(personId));
    }

}
