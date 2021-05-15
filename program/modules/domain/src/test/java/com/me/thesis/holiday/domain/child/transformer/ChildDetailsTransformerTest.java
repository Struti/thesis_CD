package com.me.thesis.holiday.domain.child.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.child.domain.ChildDetails;
import com.me.thesis.holiday.dto.child.ChildDetailsDto;

/**
 * The type Child details transformer test.
 */
class ChildDetailsTransformerTest {

    private static final Long PERSON_ID = 1111L;
    private static final Long CHILD_ID_1 = 11111L;
    private static final LocalDate BIRTH_DATE = LocalDate.of(2021, 1, 25);
    private static final LocalDate EXPECTED_DATE = LocalDate.of(2021, 1, 25);
    private static final LocalDate DISABILITY_CERT_EXPIRATION_DATE = LocalDate.of(2021, 1, 30);

    @InjectMocks
    private ChildDetailsTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransformDto() {
        //GIVEN
        ChildDetailsDto dto = createBornChildDto();
        ChildDetails expected = createBornChild();

        //WHEN
        ChildDetails actual = underTest.transform(dto);
        //THEN
        assertEquals(expected, actual);
    }

    private ChildDetailsDto createBornChildDto() {
        return ChildDetailsDto.builder()
            .childId(CHILD_ID_1)
            .person(PERSON_ID)
            .born(true)
            .expectedDate(EXPECTED_DATE)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .disabilityCertExpirationDate(DISABILITY_CERT_EXPIRATION_DATE)
            .build();
    }

    private ChildDetails createBornChild() {
        return ChildDetails.builder()
            .childId(CHILD_ID_1)
            .born(true)
            .expectedDate(EXPECTED_DATE)
            .birthDate(BIRTH_DATE)
            .disability(false)
            .disabilityCertExpirationDate(DISABILITY_CERT_EXPIRATION_DATE)
            .build();
    }

}
