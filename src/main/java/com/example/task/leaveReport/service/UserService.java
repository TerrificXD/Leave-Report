package com.example.task.leaveReport.service;

import com.example.task.leaveReport.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto UpdateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}
