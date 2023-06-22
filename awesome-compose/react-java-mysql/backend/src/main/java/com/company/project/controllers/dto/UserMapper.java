
package com.company.project.controllers.dto;

import com.company.project.entity.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());

        return userDTO;
    }
}
