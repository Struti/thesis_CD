package com.me.thesis.holiday.controller.holidayhistory;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dto.holidayhistory.DeathEventDto;
import com.me.thesis.holiday.service.holidayhistory.DeathEventService;

class DeathEventControllerTest {

    @InjectMocks
    private DeathEventController underTest;

    @Mock
    private DeathEventService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDeathEventShould() {
        //GIVEN
        DeathEventDto dto = createDto();
        //WHEN
        verifyNoInteractions(service);
        underTest.addDeathEvent(dto);
        //THEN
        verify(service).addEvent(dto);
        verifyNoMoreInteractions(service);
    }

    private DeathEventDto createDto() {
        return DeathEventDto.builder()
            .build();
    }

}
