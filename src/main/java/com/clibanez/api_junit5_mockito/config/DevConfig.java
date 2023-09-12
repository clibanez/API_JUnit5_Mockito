package com.clibanez.api_junit5_mockito.config;

import com.clibanez.api_junit5_mockito.Repositories.UserRepository;
import com.clibanez.api_junit5_mockito.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("dev")
public class DevConfig {
    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB(){
        User user1 = new User(1,"Clibanes","clibanez@gmail.com","123");
        User user2 = new User(2,"Matheus","matheus@gmail.com","123");

        userRepository.saveAll(List.of(user1,user2));


    }
}
