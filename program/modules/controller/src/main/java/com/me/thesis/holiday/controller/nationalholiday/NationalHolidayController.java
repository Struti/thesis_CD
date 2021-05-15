package com.me.thesis.holiday.controller.nationalholiday;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.domain.nationalholiday.domain.NationalHoliday;
import com.me.thesis.holiday.domain.nationalholiday.transformer.NationalHolidayTransformer;
import com.me.thesis.holiday.dto.nationalholiday.NationalHolidayDto;
import com.me.thesis.holiday.service.nationalholiday.NationalHolidayService;

/**
 * The type National holiday controller.
 */
@RestController
public class NationalHolidayController {

    private static final String NATIONAL_HOLIDAYS_GET_NATIONAL_HOLIDAYS = "/nationalHolidays/getNationalHolidays";
    private static final String ADMIN_NATIONAL_HOLIDAYS_SAVE_ALL = "/admin/nationalHolidays/saveAll";
    private static final String ADMIN_NATIONAL_HOLIDAYS_SAVE = "/admin/nationalHolidays/save";
    private static final String ADMIN_NATIONAL_HOLIDAYS_DELETE_ID = "/admin/nationalHolidays/delete/{id}";

    @Autowired
    private NationalHolidayService service;

    @Autowired
    private NationalHolidayTransformer transformer;

    /**
     * Gets national holidays.
     *
     * @return the national holidays
     */
    @GetMapping(NATIONAL_HOLIDAYS_GET_NATIONAL_HOLIDAYS)
    public List<NationalHoliday> getNationalHolidays() {
        return service.getNationalHolidays();
    }

    /**
     * Save all.
     *
     * @param nationalHolidays the national holidays
     */
    @PostMapping(ADMIN_NATIONAL_HOLIDAYS_SAVE_ALL)
    public void saveAll(@RequestBody List<NationalHolidayDto> nationalHolidays) {
        service.saveNationalHolidays(nationalHolidays.stream()
            .map(transformer::transform)
            .collect(Collectors.toList()));
    }

    /**
     * Save.
     *
     * @param nationalHoliday the national holiday
     */
    @PostMapping(ADMIN_NATIONAL_HOLIDAYS_SAVE)
    public void save(@RequestBody NationalHolidayDto nationalHoliday) {
        service.saveNationalHoliday(transformer.transform(nationalHoliday));
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping(ADMIN_NATIONAL_HOLIDAYS_DELETE_ID)
    public void delete(@PathVariable("id") Long id) {
        service.deleteNationalHoliday(id);
    }

}
