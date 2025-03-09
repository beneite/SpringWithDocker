package com.beneite.SpringWithDocker.service;


import com.beneite.SpringWithDocker.dto.requestDto.UserDto;
import com.beneite.SpringWithDocker.dto.requestDto.UserIdRequestDto;
import com.beneite.SpringWithDocker.dto.responseDto.UserIdResponseDto;

import java.util.List;

public interface UserService {

    UserDto createUserImplementation(UserDto userEntity);
    UserDto getUserByIdImplementation(Long userId);
    List<UserDto> getAllUsersImplementation();
    UserDto updateUserImplementation(UserDto userDto);
    void deleteUserImplementation(Long userId);
    UserIdResponseDto getUserEmailFromIdImplementation(UserIdRequestDto userIdRequestDto);

}
