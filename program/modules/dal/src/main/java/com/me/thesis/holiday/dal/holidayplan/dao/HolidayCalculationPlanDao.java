package com.me.thesis.holiday.dal.holidayplan.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidayplan.domain.HolidayCalculationPlanEntity;
import com.me.thesis.holiday.dal.holidayplan.repository.HolidayCalculationPlanRepository;

/**
 * The type Holiday calculation plan dao.
 */
@Component
public class HolidayCalculationPlanDao {

    @Autowired
    private HolidayCalculationPlanRepository repository;

    /**
     * Gets holiday calculation plan.
     *
     * @return the holiday calculation plan
     */
    public List<HolidayCalculationPlanEntity> getHolidayCalculationPlan() {
        return repository.findAll();
    }

    /**
     * Save holiday calculation plan.
     *
     * @param calculationPlan the calculation plan
     */
    public void saveHolidayCalculationPlan(HolidayCalculationPlanEntity calculationPlan) {
        repository.save(calculationPlan);
    }

    /**
     * Delete holiday calculation plan.
     *
     * @param calculationPlanId the calculation plan id
     */
    public void deleteHolidayCalculationPlan(Long calculationPlanId) {
        repository.deleteById(calculationPlanId);
    }

    /**
     * Gets plan by id.
     *
     * @param calculationPlanId the calculation plan id
     *
     * @return the plan by id
     */
    public HolidayCalculationPlanEntity getPlanById(Long calculationPlanId) {
        return repository.findById(calculationPlanId)
            .orElseThrow(NoSuchElementException::new);
    }

}
