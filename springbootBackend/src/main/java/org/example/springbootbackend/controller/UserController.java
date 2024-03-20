package org.example.springbootbackend.controller;

import org.example.springbootbackend.dto.UserCreationDTO;
import org.example.springbootbackend.dto.UserDTO;
import org.example.springbootbackend.mapper.UserMapper;
import org.example.springbootbackend.model.User;
import org.example.springbootbackend.repo.UserRepository;
import org.example.springbootbackend.security.AuthenticationRequest;
import org.example.springbootbackend.security.AuthenticationResponse;
import org.example.springbootbackend.service.AuthenticationService;
import org.example.springbootbackend.service.UserService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationService authService;
    private final UserMapper userMapper = new UserMapper();
    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody UserCreationDTO userDTO) {
        logger.info("User " + userDTO + " has been registered!");
        //TODO: if user already exists, return response entity stating that to frontend.
        try {
            AuthenticationResponse authResponse = authService.register(userDTO);
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            logger.warn("Internal error has occurred: " + e.getMessage());
            e.getStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> getUser(@RequestBody UserDTO userDTO) {
        logger.info("User " + userDTO.getUsername() + " has attempted to log in!");
        System.out.println(userDTO.getUsername() + " " + userDTO.getPassword());
        try {
            AuthenticationResponse authResponse = authService.authenticate(AuthenticationRequest
                    .builder()
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .build());
            System.out.println(authResponse.toString());
            logger.info("User " + userDTO.getUsername() + " has logged in!");
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            logger.warn("Internal error has occurred: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    // UserController.java

    @GetMapping("/users/by-username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





}
