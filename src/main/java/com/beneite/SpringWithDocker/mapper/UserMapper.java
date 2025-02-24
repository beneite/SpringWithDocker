package com.beneite.SpringWithDocker.mapper;

import com.beneite.SpringWithDocker.dto.UserDto;
import com.beneite.SpringWithDocker.entity.UserEntity;

public class UserMapper {

    /**
     * method to convert DTO object to JPA
     * @param userDto - DTO object
     * @return - JPA object after conversion
     */
    public static UserEntity convertToJpa(UserDto userDto){
        UserEntity userEntity = new UserEntity(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );

        return userEntity;
    }

    /**
     * method to convert JPA object to DTO
     * @param userEntity - JPA object
     * @return - DTO object after conversion
     */
    public static UserDto convertToDto(UserEntity userEntity){
        UserDto userDto = new UserDto(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail()
        );

        return userDto;
    }
}
