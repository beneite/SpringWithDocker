package com.beneite.SpringWithDocker.mapper;

import com.beneite.SpringWithDocker.dto.requestDto.CreateEmployeeRequestDto;
import com.beneite.SpringWithDocker.dto.requestDto.UserDto;
import com.beneite.SpringWithDocker.dto.responseDto.CreateEmployeeResponseDto;
import com.beneite.SpringWithDocker.dto.responseDto.GetEmployeeResponseDto;
import com.beneite.SpringWithDocker.entity.EmployeeEntity;
import com.beneite.SpringWithDocker.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToDto(UserEntity userEntity);

    UserEntity mapToJpa(UserDto userDto);

    CreateEmployeeResponseDto mapToDto(EmployeeEntity employeeEntity);

    EmployeeEntity mapToJpa(CreateEmployeeRequestDto createEmployeeRequestDto);

    GetEmployeeResponseDto mapToGetAllEmployeeResponseDto(EmployeeEntity employeeEntity);

    GetEmployeeResponseDto mapToGetEmployeeResponseDto(EmployeeEntity employeeEntity);
}
