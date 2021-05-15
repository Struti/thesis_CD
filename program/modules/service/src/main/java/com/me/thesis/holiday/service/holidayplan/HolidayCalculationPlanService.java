package com.me.thesis.holiday.service.holidayplan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.holidayplan.dao.HolidayCalculationPlanDao;
import com.me.thesis.holiday.dal.holidayplan.transfromer.HolidayCalculationPlanEntityTransformer;
import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;

/**
 * The type Holiday calculation plan service.
 */
@Service
public class HolidayCalculationPlanService {

    @Autowired
    private HolidayCalculationPlanDao dao;

    @Autowired
    private HolidayCalculationPlanEntityTransformer transformer;

    /**
     * Gets holiday calculation plan.
     *
     * @return the holiday calculation plan
     */
    public List<HolidayCalculationPlan> getHolidayCalculationPlan() {
        return transformer.transform(dao.getHolidayCalculationPlan());
    }

    /**
     * Save holiday calculation plan.
     *
     * @param holidayCalculationPlan the holiday calculation plan
     */
    public void saveHolidayCalculationPlan(HolidayCalculationPlan holidayCalculationPlan) {
        dao.saveHolidayCalculationPlan(transformer.transform(holidayCalculationPlan));
    }

    /**
     * Delete holiday calculation plan.
     *
     * @param calculationPlanId the calculation plan id
     */
    public void deleteHolidayCalculationPlan(Long calculationPlanId) {
        dao.deleteHolidayCalculationPlan(calculationPlanId);
    }

    /**
     * Gets plan by id.
     *
     * @param calculationPlanId the calculation plan id
     *
     * @return the plan by id
     */
    public HolidayCalculationPlan getPlanById(Long calculationPlanId) {
        return transformer.transform(dao.getPlanById(calculationPlanId));
    }

}
