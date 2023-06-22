package com.company.project.services;

import java.util.List;
import java.util.Optional;
import com.company.project.entity.User;
import com.company.project.repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;


    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
