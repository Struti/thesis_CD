package com.me.thesis.holiday.service.password;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * The type Password generator test.
 */
class PasswordGeneratorTest {

    private static final int EXPECTED_PASSWORD_LENGTH = 8;

    @InjectMocks
    private PasswordGenerator underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFirstPasswordShouldCreateEightLongString() {
        //GIVEN
        //WHEN
        String actual = underTest.createFirstPassword();
        //THEN
        assertEquals(EXPECTED_PASSWORD_LENGTH, actual.length());
    }
}
