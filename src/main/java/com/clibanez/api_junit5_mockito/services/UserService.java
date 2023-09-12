package com.clibanez.api_junit5_mockito.services;

import com.clibanez.api_junit5_mockito.domain.User;

public interface UserService {
    User findById(Integer id);
}
