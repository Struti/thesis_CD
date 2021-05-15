package com.me.thesis.holiday.service.scheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.service.holidayhistory.ChildBornHolidaysService;
import com.me.thesis.holiday.service.holidayhistory.UnusedHolidayService;

class DailyChildBornHolidaysSchedulerTest {

    @InjectMocks
    private DailyChildBornHolidaysScheduler underTest;

    @Mock
    private ChildBornHolidaysService childBornHolidaysService;

    @Mock
    private UnusedHolidayService unusedHolidaysService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testScheduleShould() {
        //GIVEN
        //WHEN
        verifyNoInteractions(childBornHolidaysService, unusedHolidaysService);
        underTest.schedule();
        //THEN
        verify(unusedHolidaysService).addPenalty();
        verify(childBornHolidaysService).validate();
        verifyNoMoreInteractions(childBornHolidaysService, unusedHolidaysService);
    }

}
