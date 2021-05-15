package com.me.thesis.holiday.dal.user.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.thesis.holiday.dal.holidayhistory.domain.HolidayHistoryEntity;
import com.me.thesis.holiday.dal.holidayhistory.transformer.HolidayHistoryEntityTransformer;
import com.me.thesis.holiday.dal.person.domain.PersonEntity;
import com.me.thesis.holiday.dal.person.transformer.facade.PersonEntityTransformerFacade;
import com.me.thesis.holiday.dal.user.domain.UserEntity;
import com.me.thesis.holiday.dal.user.enums.UserEntityRole;
import com.me.thesis.holiday.domain.person.domain.PersonDetails;
import com.me.thesis.holiday.domain.user.domain.CreateUserDomain;
import com.me.thesis.holiday.domain.user.domain.CreateUserHelper;
import com.me.thesis.holiday.domain.user.domain.DefaultUserDetails;
import com.me.thesis.holiday.domain.user.enums.UserDomainRole;

/**
 * The type User entity transformer.
 */
@Component
public class UserEntityTransformer {

    @Autowired
    private UserEntityRoleTransformer transformer;

    @Autowired
    private PersonEntityTransformerFacade personEntityTransformerFacade;

    @Autowired
    private HolidayHistoryEntityTransformer holidayHistoryEntityTransformer;

    /**
     * Transform user entity.
     *
     * @param createUserHelper the create user helper
     *
     * @return the user entity
     */
    public UserEntity transform(CreateUserHelper createUserHelper) {
        CreateUserDomain user = createUserHelper.getUser();
        PersonDetails personDetails = createUserHelper.getPersonDetails();
        PersonEntity person = personEntityTransformerFacade.transformFullDomainDetailsToEntity(personDetails);
        HolidayHistoryEntity historyEntity = holidayHistoryEntityTransformer.transform(createUserHelper.getHolidayHistory());
        historyEntity.setPersonEntity(person);
        person.setHolidayHistoryEntity(historyEntity);
        return UserEntity.builder()
            .email(user.getEmail())
            .password(createUserHelper.getPassword())
            .role(getRole(user.getRole()))
            .personEntity(person)
            .build();
    }

    public DefaultUserDetails transform(UserEntity userEntity) {
        PersonDetails personDetails = personEntityTransformerFacade.transformFullEntityDetailsToDomain(userEntity.getPersonEntity());
        return DefaultUserDetails.builder()
            .email(userEntity.getUsername())
            .password(userEntity.getPassword())
            .person(personDetails)
            .role(getRole(userEntity.getRole()))
            .build();
    }

    private UserEntityRole getRole(UserDomainRole role) {
        return transformer.transform(role);
    }

    private UserDomainRole getRole(UserEntityRole role) {
        return transformer.transform(role);
    }

}
