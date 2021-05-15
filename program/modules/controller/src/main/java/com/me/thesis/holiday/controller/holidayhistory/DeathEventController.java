package com.me.thesis.holiday.controller.holidayhistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.thesis.holiday.dto.holidayhistory.DeathEventDto;
import com.me.thesis.holiday.service.holidayhistory.DeathEventService;

@RestController
public class DeathEventController {

    @Autowired
    private DeathEventService service;

    @PostMapping("/admin/deathEvent/add")
    public void addDeathEvent(@RequestBody DeathEventDto deathEventDto) {
        service.addEvent(deathEventDto);
    }

}
