package com.clibanez.api_junit5_mockito.resources;

import com.clibanez.api_junit5_mockito.config.ModelMapperConfig;
import com.clibanez.api_junit5_mockito.domain.User;
import com.clibanez.api_junit5_mockito.domain.dto.UserDTO;
import com.clibanez.api_junit5_mockito.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class));

    }
}
