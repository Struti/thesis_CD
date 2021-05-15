package com.me.thesis.holiday.dal.user.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.thesis.holiday.dal.holidayhistory.domain.HolidayHistoryEntity;
import com.me.thesis.holiday.dal.holidayhistory.transformer.HolidayHistoryEntityTransformer;
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.enums.PersonEntityEmployeeRole;
import com.me.thesis.holiday.dal.person.enums.PersonEntityGender;
import com.me.thesis.holiday.dal.person.transformer.facade.PersonEntityTransformerFacade;
import com.me.thesis.holiday.dal.user.domain.UserEntity;
import com.me.thesis.holiday.dal.user.enums.UserEntityRole;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.user.domain.CreateUserDomain;
import com.me.thesis.holiday.domain.user.domain.CreateUserHelper;
import com.me.thesis.holiday.domain.user.domain.DefaultUserDetails;
import com.me.thesis.holiday.domain.user.enums.UserDomainRole;

/**
 * The type User entity transformer test.
 */
class UserEntityTransformerTest {

    private static final String USER_EMAIL = "test@test.com";
    private static final String PASSWORD = "Test123";
    private static final String NAME = "Test";
    private static final UserDomainRole USER_DOMAIN_ROLE = UserDomainRole.USER;
    private static final UserEntityRole USER_ENTITY_ROLE = UserEntityRole.USER;

    @InjectMocks
    private UserEntityTransformer underTest;

    @Mock
    private UserEntityRoleTransformer transformer;

    @Mock
    private PersonEntityTransformerFacade personEntityTransformerFacade;

    @Mock
    private HolidayHistoryEntityTransformer holidayHistoryEntityTransformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserDomainToEntityWithUserRole() {
        //GIVEN
        CreateUserHelper createUser = createUserHelper();
        UserEntity expected = createUserEntityWithUserRole();
        when(transformer.transform(USER_DOMAIN_ROLE)).thenReturn(USER_ENTITY_ROLE);
        when(personEntityTransformerFacade.transformFullDomainDetailsToEntity(createUser.getPersonDetails())).thenReturn(expected.getPersonEntity());
        when(holidayHistoryEntityTransformer.transform(createUser.getHolidayHistory())).thenReturn(expected.getPersonEntity()
            .getHolidayHistoryEntity());
        //WHEN
        verifyNoInteractions(transformer, personEntityTransformerFacade, holidayHistoryEntityTransformer);
        UserEntity actual = underTest.transform(createUser);
        //THEN
        verify(transformer).transform(USER_DOMAIN_ROLE);
        verify(personEntityTransformerFacade).transformFullDomainDetailsToEntity(createUser.getPersonDetails());
        verify(holidayHistoryEntityTransformer).transform(createUser.getHolidayHistory());
        verifyNoMoreInteractions(transformer, personEntityTransformerFacade, holidayHistoryEntityTransformer);
        assertEquals(expected, actual);
    }

    private CreateUserHelper createUserHelper() {
        return CreateUserHelper.builder()
            .password(PASSWORD)
            .user(createUserDomain())
            .currentDate(LocalDate.EPOCH)
            .build();
    }

    private CreateUserDomain createUserDomain() {
        return CreateUserDomain.builder()
            .email(USER_EMAIL)
            .role(USER_DOMAIN_ROLE)
            .name(NAME)
            .build();
    }

    private UserEntity createUserEntityWithUserRole() {
        return UserEntity.builder()
            .email(USER_EMAIL)
            .password(PASSWORD)
            .role(USER_ENTITY_ROLE)
            .personEntity(createPersonEntity())
            .build();
    }

    private PersonEntity createPersonEntity() {
        return PersonEntity.builder()
            .fullName(NAME)
            .birthName(NAME)
            .gender(PersonEntityGender.MALE)
            .birthDate(LocalDate.EPOCH)
            .disability(false)
            .startDate(LocalDate.EPOCH)
            .employeeRole(PersonEntityEmployeeRole.EMPLOYEE)
            .holidayHistoryEntity(createHolidayHistoryEntity())
            .build();
    }

    private HolidayHistoryEntity createHolidayHistoryEntity() {
        return HolidayHistoryEntity.builder()
            .build();
    }

    @Test
    void testTransformShouldTransformEntityToUserDetails() {
        //GIVEN
        UserEntity entity = createUserEntityWithUserRole();
        DefaultUserDetails expected = createDefaultUserDetails();
        when(personEntityTransformerFacade.transformFullEntityDetailsToDomain(entity.getPersonEntity())).thenReturn(createPersonDetails());
        when(transformer.transform(UserEntityRole.USER)).thenReturn(USER_DOMAIN_ROLE);
        //WHEN
        verifyNoInteractions(transformer, personEntityTransformerFacade, holidayHistoryEntityTransformer);
        DefaultUserDetails actual = underTest.transform(entity);
        //THEN
        verify(transformer).transform(UserEntityRole.USER);
        verify(personEntityTransformerFacade).transformFullEntityDetailsToDomain(entity.getPersonEntity());
        verifyNoMoreInteractions(transformer, personEntityTransformerFacade, holidayHistoryEntityTransformer);
        assertEquals(expected.getPerson(), actual.getPerson());
        assertEquals(expected.getUsername(), actual.getUsername());
    }

    private DefaultUserDetails createDefaultUserDetails() {
        return DefaultUserDetails.builder()
            .email(USER_EMAIL)
            .role(UserDomainRole.USER)
            .password(PASSWORD)
            .person(createPersonDetails())
            .build();
    }

    private PersonDetails createPersonDetails() {
        return PersonDetails.builder()
            .build();
    }

}
