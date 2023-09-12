package com.clibanez.api_junit5_mockito.services.impl;

import com.clibanez.api_junit5_mockito.Repositories.UserRepository;
import com.clibanez.api_junit5_mockito.domain.User;
import com.clibanez.api_junit5_mockito.resources.UserResource;
import com.clibanez.api_junit5_mockito.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElse(null);
    }
}
