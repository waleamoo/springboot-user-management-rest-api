package com.techqwerty.springboorestapi.service;

import java.util.List;
import com.techqwerty.springboorestapi.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    void deleteUser(Long userId);
}
