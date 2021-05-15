package com.me.thesis.holiday.dal.holidayhistory.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidayhistory.domain.HolidayHistoryEntity;
import com.me.thesis.holiday.dal.person.transformer.facade.PersonEntityTransformerFacade;
import com.me.thesis.holiday.domain.holidayhistory.domain.HolidayHistory;

/**
 * The type Holiday history entity transformer.
 */
@Component
public class HolidayHistoryEntityTransformer {

    @Autowired
    private YearHistoryTransformer yearHistoryTransformer;

    @Autowired
    private PersonEntityTransformerFacade personEntityTransformerFacade;

    /**
     * Transform holiday history entity.
     *
     * @param holidayHistory the holiday history
     *
     * @return the holiday history entity
     */
    public HolidayHistoryEntity transform(HolidayHistory holidayHistory) {
        return HolidayHistoryEntity.builder()
            .holidayHistoryId(holidayHistory.getId())
            .personEntity(personEntityTransformerFacade.transformFullDomainDetailsToEntity(holidayHistory.getPerson()))
            .history(yearHistoryTransformer.transform(holidayHistory.getYearHistories()))
            .build();
    }

    public HolidayHistory transform(HolidayHistoryEntity holidayHistory) {
        return HolidayHistory.builder()
            .id(holidayHistory.getHolidayHistoryId())
            .person(personEntityTransformerFacade.transformFullEntityDetailsToDomain(holidayHistory.getPersonEntity()))
            .yearHistories(yearHistoryTransformer.transform(holidayHistory.getHistory()))
            .build();
    }

}
