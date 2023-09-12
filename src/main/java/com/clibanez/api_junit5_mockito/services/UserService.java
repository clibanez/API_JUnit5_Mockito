package com.clibanez.api_junit5_mockito.services;

import com.clibanez.api_junit5_mockito.domain.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
}
