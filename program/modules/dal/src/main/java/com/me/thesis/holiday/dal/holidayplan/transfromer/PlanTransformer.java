package com.me.thesis.holiday.dal.holidayplan.transfromer;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.me.thesis.holiday.dal.exception.JsonTransformationException;
import com.me.thesis.holiday.domain.holidayplan.domain.Plan;

/**
 * The type Plan transformer.
 */
@Component
public class PlanTransformer {

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Transform string.
     *
     * @param plan the plan
     *
     * @return the string
     */
    public String transform(Plan plan) {
        mapper.registerModule(new Jdk8Module());
        String json;
        try {
            json = mapper.writeValueAsString(plan);
        } catch (JsonProcessingException e) {
            throw new JsonTransformationException();
        }
        return json;
    }

    /**
     * Transform plan.
     *
     * @param holidayPlan the holiday plan
     *
     * @return the plan
     */
    public Plan transform(String holidayPlan) {
        mapper.registerModule(new Jdk8Module());
        Plan plan;
        try {
            plan = mapper.readValue(holidayPlan, Plan.class);
        } catch (JsonProcessingException e) {
            throw new JsonTransformationException();
        }
        return plan;
    }

}
