package com.cydeo.service;

import com.cydeo.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto findByUsername(String username);
    UserDto findByUserId(Long Id);
    List<UserDto> listUsersForCurrentUser();
    void updateUser(UserDto userDto);
}
