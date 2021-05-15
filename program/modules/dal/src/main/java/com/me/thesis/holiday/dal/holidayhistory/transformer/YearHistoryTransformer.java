package com.me.thesis.holiday.dal.holidayhistory.transformer;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.me.thesis.holiday.dal.exception.JsonTransformationException;
import com.me.thesis.holiday.domain.holidayhistory.domain.YearHistory;

/**
 * The type Year history transformer.
 */
@Component
public class YearHistoryTransformer {

    private final ObjectMapper mapper = new ObjectMapper();

    private YearHistoryTransformer() {
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new SimpleModule().addSerializer(new LocalDateSerializer(DateTimeFormatter.ISO_DATE)));
    }

    /**
     * Transform string.
     *
     * @param yearHistory the year history
     *
     * @return the string
     */
    public String transform(List<YearHistory> yearHistory) {
        String json;
        try {
            json = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(yearHistory);
        } catch (JsonProcessingException e) {
            throw new JsonTransformationException();
        }
        return json;
    }

    /**
     * Transform year history.
     *
     * @param json the json
     *
     * @return the year history
     */
    public List<YearHistory> transform(String json) {
        List<YearHistory> yearHistory;
        try {
            yearHistory = List.of(mapper.readValue(json, YearHistory[].class));
        } catch (JsonProcessingException e) {
            throw new JsonTransformationException();
        }
        return yearHistory;
    }

}
