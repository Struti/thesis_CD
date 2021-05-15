package com.me.thesis.holiday.controller.holidayplan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.holidayplan.domain.HolidayCalculationPlan;
import com.me.thesis.holiday.domain.holidayplan.transformer.HolidayCalculationPlanTransformer;
import com.me.thesis.holiday.dto.holidayplan.HolidayCalculationPlanDto;
import com.me.thesis.holiday.service.holidayplan.HolidayCalculationPlanService;

/**
 * The type Holiday calculation plan controller.
 */
@RestController
public class HolidayCalculationPlanController {

    private static final String ADMIN_HOLIDAY_PLAN_DELETE_HOLIDAY_CALCULATION_PLAN = "/admin/holidayPlan/deleteHolidayCalculationPlan/{id}";
    private static final String ADMIN_HOLIDAY_PLAN_SAVE_HOLIDAY_CALCULATION_PLAN = "/admin/holidayPlan/saveHolidayCalculationPlan";
    private static final String ADMIN_HOLIDAY_PLAN_GET_ALL_HOLIDAY_CALCULATION_PLAN = "/admin/holidayPlan/getAllHolidayCalculationPlan";

    @Autowired
    private HolidayCalculationPlanService service;

    @Autowired
    private HolidayCalculationPlanTransformer transformer;

    /**
     * Gets holiday calculation plan.
     *
     * @return the holiday calculation plan
     */
    @GetMapping(ADMIN_HOLIDAY_PLAN_GET_ALL_HOLIDAY_CALCULATION_PLAN)
    public List<HolidayCalculationPlan> getHolidayCalculationPlan() {
        return service.getHolidayCalculationPlan();
    }

    /**
     * Save holiday calculation plan.
     *
     * @param calculationPlan the calculation plan
     */
    @PostMapping(ADMIN_HOLIDAY_PLAN_SAVE_HOLIDAY_CALCULATION_PLAN)
    public void saveHolidayCalculationPlan(@RequestBody HolidayCalculationPlanDto calculationPlan) {
        service.saveHolidayCalculationPlan(transformer.transform(calculationPlan));
    }

    /**
     * Delete holiday calculation plan.
     *
     * @param planId the plan id
     */
    @DeleteMapping(ADMIN_HOLIDAY_PLAN_DELETE_HOLIDAY_CALCULATION_PLAN)
    public void deleteHolidayCalculationPlan(@PathVariable("id") Long planId) {
        service.deleteHolidayCalculationPlan(planId);
    }

}
