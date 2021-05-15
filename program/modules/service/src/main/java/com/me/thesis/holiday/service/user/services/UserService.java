package com.me.thesis.holiday.service.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.user.dao.UserDao;
import com.me.thesis.holiday.dal.user.domain.UserEntity;
import com.me.thesis.holiday.dal.user.transformer.UserEntityTransformer;
import com.me.thesis.holiday.domain.user.domain.CreateUserDomain;
import com.me.thesis.holiday.domain.user.domain.CreateUserHelper;
import com.me.thesis.holiday.dto.user.domain.ChangePasswordDto;
import com.me.thesis.holiday.service.password.PasswordGenerator;
import com.me.thesis.holiday.service.user.exceptions.EmailExistException;
import com.me.thesis.holiday.service.user.exceptions.PasswordChangeFailedException;
import com.me.thesis.holiday.service.user.factory.CreateUserFactory;

/**
 * The type User service.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserEntityTransformer transformer;

    @Autowired
    private PasswordGenerator password;

    @Autowired
    private CreateUserFactory createUserFactory;

    /**
     * Create user.
     *
     * @param createUserDomain the create user domain
     */
    public void createUser(CreateUserDomain createUserDomain) {
        checkEmailExist(createUserDomain);
        String firstPassword = password.createFirstPassword();
        CreateUserHelper createUserHelper = createUserFactory.create(createUserDomain, firstPassword);
        userDao.createUser(transformer.transform(createUserHelper));
    }

    /**
     * Change password.
     *
     * @param changePasswordDto the change password dto
     */
    public void changePassword(ChangePasswordDto changePasswordDto) {
        UserEntity user = userDao.findByEmail(changePasswordDto.getEmail());
        if (user.getPassword()
            .equals(changePasswordDto.getOldPassword())) {
            user.setPassword(changePasswordDto.getNewPassword());
            userDao.update(user);
        } else {
            throw new PasswordChangeFailedException();
        }
    }

    private void checkEmailExist(CreateUserDomain createUserDomain) {
        if (emailExist(createUserDomain.getEmail())) {
            throw new EmailExistException();
        }
    }

    private boolean emailExist(String email) {
        return userDao.emailExist(email);
    }

}
