package com.company.project.services;

import java.util.List;

import com.company.project.entity.User;

public interface UserService {
    User createUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();

}