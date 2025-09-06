package com.beneite.SpringWithDocker.service;

import com.beneite.SpringWithDocker.dto.requestDto.CreateEmployeeRequestDto;
import com.beneite.SpringWithDocker.dto.responseDto.CreateEmployeeResponseDto;
import com.beneite.SpringWithDocker.dto.responseDto.GetEmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    CreateEmployeeResponseDto createEmployeeImplementation(CreateEmployeeRequestDto createEmployeeRequestDto);
    List<GetEmployeeResponseDto> getAllEmployeeImplementation();
    GetEmployeeResponseDto getEmployeeImplementation(Long employeeId);
    GetEmployeeResponseDto getEmployeeByCompanyEmailId(String companyEmailId);

}
