package com.me.thesis.holiday.dal.person.transformer.fulltransformer;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.me.thesis.holiday.dal.exception.JsonTransformationException;
import com.me.thesis.holiday.domain.person.domain.SpecificPlan;

/**
 * The type Special plan transformer.
 */
@Component
public class SpecialPlanTransformer {

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Transform string.
     *
     * @param plan the plan
     *
     * @return the string
     */
    public String transform(SpecificPlan plan) {
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
     * Transform specific plan.
     *
     * @param holidayPlan the holiday plan
     *
     * @return the specific plan
     */
    public SpecificPlan transform(String holidayPlan) {
        mapper.registerModule(new Jdk8Module());
        SpecificPlan plan;
        try {
            plan = mapper.readValue(holidayPlan, SpecificPlan.class);
        } catch (JsonProcessingException e) {
            throw new JsonTransformationException();
        }
        return plan;
    }

}
