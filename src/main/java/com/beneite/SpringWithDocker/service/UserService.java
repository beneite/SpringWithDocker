package com.beneite.SpringWithDocker.service;


import com.beneite.SpringWithDocker.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser (UserDto userEntity);
    UserDto getUserById (Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto userDto);
    void deleteUser(Long userId);

}
