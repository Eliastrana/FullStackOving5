package org.example.springbootbackend.mapper;


import org.example.springbootbackend.dto.UserCreationDTO;
import org.example.springbootbackend.dto.UserDTO;
import org.example.springbootbackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserCreationDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
    }
    public UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername(), user.getEmail());
    }

}