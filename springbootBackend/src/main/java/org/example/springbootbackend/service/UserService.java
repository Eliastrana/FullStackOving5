package org.example.springbootbackend.service;

import org.example.springbootbackend.dto.UserDTO;
import org.example.springbootbackend.exception.UserNotFoundException;
import org.example.springbootbackend.mapper.UserMapper;
import org.example.springbootbackend.model.User;
import org.example.springbootbackend.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO findUser(UserDTO user) {
        Optional<User> userInDB = userRepository.findUserByUsername(user.getUsername());
        userInDB.ifPresent(Objects::requireNonNull);
        return userMapper.toUserDTO(userInDB
                .orElseThrow(() -> new UserNotFoundException(user.getUsername())));
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public User loadByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }


}