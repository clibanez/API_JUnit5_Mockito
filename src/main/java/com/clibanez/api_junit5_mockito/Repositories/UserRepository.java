package com.clibanez.api_junit5_mockito.Repositories;

import com.clibanez.api_junit5_mockito.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
