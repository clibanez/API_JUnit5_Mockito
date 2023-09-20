package com.clibanez.api_junit5_mockito.services.impl;

import com.clibanez.api_junit5_mockito.Repositories.UserRepository;
import com.clibanez.api_junit5_mockito.domain.User;
import com.clibanez.api_junit5_mockito.domain.dto.UserDTO;
import com.clibanez.api_junit5_mockito.services.UserService;
import com.clibanez.api_junit5_mockito.services.exception.DataIntegratyViolationException;
import com.clibanez.api_junit5_mockito.services.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + "Id/" +id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        findByEmail(userDTO);
        return userRepository.save(modelMapper.map(userDTO,User.class));
    }

    @Override
    public User update(UserDTO userDTO) {
        findByEmail(userDTO);
      return userRepository.save(modelMapper.map(userDTO,User.class));
    }

    @Override
    public void delete(Integer id) {
        System.out.println(id);
       userRepository.deleteById(id);
    }

    public void findByEmail(UserDTO userDTO){
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        if(user.isPresent() && !user.get().getId().equals(userDTO.getId())){
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
