package com.me.thesis.holiday.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.me.thesis.holiday.dal.user.domain.UserEntity;
import com.me.thesis.holiday.dal.user.repository.UserRepository;
import com.me.thesis.holiday.dal.user.transformer.UserEntityTransformer;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityTransformer transformer;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserEntity user = getUser(userEmail);
        return transformer.transform(user);
    }

    public UserEntity getUser(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

}
