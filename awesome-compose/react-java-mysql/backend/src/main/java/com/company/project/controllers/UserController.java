package com.company.project.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.project.controllers.dto.UserDTO;
import com.company.project.controllers.dto.UserMapper;
import com.company.project.entity.User;
import com.company.project.repository.UserRepository;
import com.company.project.services.UserService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = UserMapper.toDTO(user);
            userDTOs.add(userDTO);
        }

        return new ResponseEntity<>(userDTOs, HttpStatus.OK);

    }
}
