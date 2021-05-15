package com.me.thesis.holiday.dal.person.transformer.partialtransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.user.domain.UserEntity;
import com.me.thesis.holiday.domain.person.domain.PersonSelectorDomain;

/**
 * The type Partial person entity transformer test.
 */
class PartialPersonEntityTransformerTest {

    private static final long TEST_PERSON_2_ID = 809L;
    private static final long TEST_PERSON_1_ID = 9181L;
    private static final String TEST_PERSON_2_NAME = "Theódóra Ryba";
    private static final String TEST_PERSON_1_NAME = "Ananth Zapatero";
    private static final String TEST_PERSON_2_EMAIL = "Theodora.ryba@test.com";
    private static final String TEST_PERSON_1_EMAIL = "ananth.zapatero@test.com";

    @InjectMocks
    private PartialPersonEntityTransformer underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformShouldTransFormUserEntityListToPersonSelectorDomainSet() {
        //GIVEN
        List<UserEntity> entities = createUserEntityList();
        Set<PersonSelectorDomain> expected = createPersonSelectorDomains();
        //WHEN
        Set<PersonSelectorDomain> actual = underTest.transform(entities);
        //THEN
        assertEquals(expected, actual);
    }

    private Set<PersonSelectorDomain> createPersonSelectorDomains() {
        return Set.of(createPersonSelectorDomain(TEST_PERSON_1_EMAIL, TEST_PERSON_1_ID, TEST_PERSON_1_NAME),
            createPersonSelectorDomain(TEST_PERSON_2_EMAIL, TEST_PERSON_2_ID, TEST_PERSON_2_NAME));
    }

    private PersonSelectorDomain createPersonSelectorDomain(String email, long id, String name) {
        return PersonSelectorDomain.builder()
            .id(id)
            .email(email)
            .name(name)
            .build();
    }

    private List<UserEntity> createUserEntityList() {
        return List.of(createUserEntity(TEST_PERSON_1_EMAIL, createPersonEntity(TEST_PERSON_1_ID, TEST_PERSON_1_NAME))
            , createUserEntity(TEST_PERSON_2_EMAIL, createPersonEntity(TEST_PERSON_2_ID, TEST_PERSON_2_NAME)));
    }

    private UserEntity createUserEntity(String email, PersonEntity entity) {
        return UserEntity.builder()
            .personEntity(entity)
            .email(email)
            .build();
    }

    private PersonEntity createPersonEntity(Long id, String name) {
        return PersonEntity.builder()
            .personId(id)
            .fullName(name)
            .build();
    }
}
