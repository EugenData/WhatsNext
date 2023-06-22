package com.company.project.services;

import java.util.List;

import com.company.project.entity.User;

public interface UserService {
    

    User getUserById(Long userId);

    List<User> getAllUsers();

}