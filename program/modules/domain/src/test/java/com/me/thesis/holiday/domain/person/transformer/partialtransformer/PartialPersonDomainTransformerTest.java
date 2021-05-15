package com.me.thesis.holiday.domain.person.transformer.partialtransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.domain.person.domain.PersonSelectorDomain;
import com.me.thesis.holiday.dto.person.domain.PersonSelectorDto;

/**
 * The type Partial person domain transformer test.
 */
class PartialPersonDomainTransformerTest {

    private static final long TEST_PERSON_2_ID = 809L;
    private static final long TEST_PERSON_1_ID = 9181L;
    private static final String TEST_PERSON_2_NAME = "Theódóra Ryba";
    private static final String TEST_PERSON_1_NAME = "Ananth Zapatero";
    private static final String TEST_PERSON_2_EMAIL = "Theodora.ryba@test.com";
    private static final String TEST_PERSON_1_EMAIL = "ananth.zapatero@test.com";

    @InjectMocks
    private PartialPersonDomainTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransFormUserDtoToPersonSelectorDomainSet() {
        //GIVEN
        Set<PersonSelectorDomain> domains = createPersonSelectorDomains();
        Set<PersonSelectorDto> expected = createPersonSelectorDtos();
        //WHEN
        Set<PersonSelectorDto> actual = underTest.transform(domains);
        //THEN
        assertEquals(expected, actual);
    }

    private Set<PersonSelectorDto> createPersonSelectorDtos() {
        return Set.of(createPersonSelectorDto(TEST_PERSON_1_EMAIL, TEST_PERSON_1_ID, TEST_PERSON_1_NAME),
            createPersonSelectorDto(TEST_PERSON_2_EMAIL, TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private Set<PersonSelectorDomain> createPersonSelectorDomains() {
        return Set.of(createPersonSelectorDomain(TEST_PERSON_1_EMAIL, TEST_PERSON_1_ID, TEST_PERSON_1_NAME),
            createPersonSelectorDomain(TEST_PERSON_2_EMAIL, TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private PersonSelectorDto createPersonSelectorDto(String email, long id, String name) {
        return PersonSelectorDto.builder()
            .id(id)
            .email(email)
            .name(name)
            .build();
    }

    private PersonSelectorDomain createPersonSelectorDomain(String email, long id, String name) {
        return PersonSelectorDomain.builder()
            .id(id)
            .email(email)
            .name(name)
            .build();
    }
}
