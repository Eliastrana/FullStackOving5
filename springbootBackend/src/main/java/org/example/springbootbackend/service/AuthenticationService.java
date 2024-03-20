package org.example.springbootbackend.service;


import org.example.springbootbackend.dto.UserCreationDTO;
import org.example.springbootbackend.model.User;
import org.example.springbootbackend.repo.UserRepository;
import org.example.springbootbackend.security.AuthenticationRequest;
import org.example.springbootbackend.security.AuthenticationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Service
public class AuthenticationService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);


    @Transactional
    public AuthenticationResponse register(UserCreationDTO userCreateDTO) {
        User user = User
                .builder()
                .username(userCreateDTO.getUsername())
                .password(passwordEncoder.encode(userCreateDTO.getPassword()))
                .email(userCreateDTO.getEmail())
                .build();
        if (userRepository.findUserByUsername(userCreateDTO.getUsername()).isPresent())
            throw new IllegalArgumentException("Username already exists");
        userRepository.save(user);

        logger.info(String.format("User %s has been saved in the DB!", user.getUsername()));

        String jwtToken = jwtService.generateToken(user);
        logger.info("Their JWT is: " + jwtToken);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        logger.info("Authenticating user information for " + request.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        String jwtToken = jwtService.generateToken(user);
        logger.info("New token " + jwtToken);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}