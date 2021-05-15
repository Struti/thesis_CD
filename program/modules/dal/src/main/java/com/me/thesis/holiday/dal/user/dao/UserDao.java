package com.me.thesis.holiday.dal.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.user.domain.UserEntity;
import com.me.thesis.holiday.dal.user.repository.UserRepository;

/**
 * The type User dao.
 */
@Service
public class UserDao {

    @Autowired
    private UserRepository repository;

    /**
     * Create user.
     *
     * @param user the user
     */
    public void createUser(UserEntity user) {
        repository.save(user);
    }

    /**
     * Email exist boolean.
     *
     * @param email the email
     *
     * @return the boolean
     */
    public boolean emailExist(String email) {
        return repository.existsByEmail(email);
    }

    /**
     * Find all user list.
     *
     * @return the list
     */
    public List<UserEntity> findAllUser() {
        return repository.findAll();
    }

    /**
     * Find by email user entity.
     *
     * @param email the email
     *
     * @return the user entity
     */
    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email);
    }

    /**
     * Update.
     *
     * @param user the user
     */
    public void update(UserEntity user) {
        repository.save(user);
    }

}
