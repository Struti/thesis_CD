package com.me.thesis.holiday.service.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.service.holidayhistory.ChildBornHolidaysService;
import com.me.thesis.holiday.service.holidayhistory.UnusedHolidayService;

@Service
public class DailyChildBornHolidaysScheduler {

    @Autowired
    private ChildBornHolidaysService childBornHolidaysService;

    @Autowired
    private UnusedHolidayService unusedHolidaysService;

    @Scheduled(cron = "1 0 0 * * ?")
    public void schedule() {
        unusedHolidaysService.addPenalty();
        childBornHolidaysService.validate();
    }

}
