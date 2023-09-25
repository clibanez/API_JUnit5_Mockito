package com.clibanez.api_junit5_mockito.services.impl;

import com.clibanez.api_junit5_mockito.Repositories.UserRepository;
import com.clibanez.api_junit5_mockito.domain.User;
import com.clibanez.api_junit5_mockito.domain.dto.UserDTO;
import com.clibanez.api_junit5_mockito.services.exception.DataIntegratyViolationException;
import com.clibanez.api_junit5_mockito.services.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Clibanez";
    public static final String EMAIL = "clibanez@gmail.com";
    public static final String PASSWORD = "123";

    public static final String EXCEPTIONMESSAGE = "Objeto não encontrado";
    public static final Integer INDEX = 0;
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema!";

    public static final String OBJETO_NAO_ENCONTRADO = "OBECTO NÃO ENCONTRADO";


    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    private User user;

    private UserDTO userDTO;

    private Optional<User> optionalUser;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);

        User response = userServiceImpl.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(EXCEPTIONMESSAGE));
        try {
            userRepository.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(EXCEPTIONMESSAGE,ex.getMessage());

        }
    }



    @Test
    void whenFindAllThenReturnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> response = userRepository.findAll();

        assertNotNull(response);

        assertEquals(1,response.size());
        assertEquals(User.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL,response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSucess() {
        when(userRepository.save(any())).thenReturn(user);

        User response = userServiceImpl.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnDataIntegratyViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            userServiceImpl.create(userDTO);
        }catch(Exception ex){
            assertEquals(DataIntegratyViolationException.class,ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSucess() {
        when(userRepository.save(any())).thenReturn(user);

        User response = userServiceImpl.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnDataIntegratyViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            userServiceImpl.create(userDTO);
        }catch(Exception ex){
            assertEquals(DataIntegratyViolationException.class,ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }

    @Test
    void deleteWhithSucess() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(userRepository).deleteById(anyInt());
        userServiceImpl.delete(ID);
        verify(userRepository,times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithObjectNotFaoundException(){
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            userServiceImpl.delete(ID);
        }catch(Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }


    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}