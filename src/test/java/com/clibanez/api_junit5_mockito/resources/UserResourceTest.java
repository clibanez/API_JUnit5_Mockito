package com.clibanez.api_junit5_mockito.resources;

import com.clibanez.api_junit5_mockito.Repositories.UserRepository;
import com.clibanez.api_junit5_mockito.domain.User;
import com.clibanez.api_junit5_mockito.domain.dto.UserDTO;
import com.clibanez.api_junit5_mockito.services.impl.UserServiceImpl;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Clibanez";
    public static final String EMAIL = "clibanez@gmail.com";
    public static final String PASSWORD = "123";

    public static final Integer INDEX = 0;

    private User user;

    private UserDTO userDTO;


    @InjectMocks
    private UserResource userResource;
    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private ModelMapper modelMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSucess() {
        Mockito.when(userServiceImpl.findById(Mockito.anyInt())).thenReturn(user);
        when(modelMapper.map(any(),any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userResource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());

        assertEquals(ID,response.getBody().getId());
        assertEquals(NAME,response.getBody().getName());
        assertEquals(EMAIL,response.getBody().getEmail());
        assertEquals(PASSWORD,response.getBody().getPassword());



    }

    @Test
    void whenFindAllThenReturnAListofUserDTO() {
        when(userServiceImpl.findAll()).thenReturn(List.of(user));
        when(modelMapper.map(any(),any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = userResource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());

    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);

    }
}