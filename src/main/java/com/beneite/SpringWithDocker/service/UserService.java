package com.beneite.SpringWithDocker.service;


import com.beneite.SpringWithDocker.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUserImplementation(UserDto userEntity);
    UserDto getUserByIdImplementation(Long userId);
    List<UserDto> getAllUsersImplementation();
    UserDto updateUserImplementation(UserDto userDto);
    void deleteUserImplementation(Long userId);

}
