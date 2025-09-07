package com.beneite.SpringWithDocker.controller;

import com.beneite.SpringWithDocker.dto.requestDto.CreateEmployeeRequestDto;
import com.beneite.SpringWithDocker.dto.requestDto.UserDto;
import com.beneite.SpringWithDocker.dto.responseDto.CreateEmployeeResponseDto;
import com.beneite.SpringWithDocker.dto.responseDto.GetEmployeeResponseDto;
import com.beneite.SpringWithDocker.service.implementation.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.beneite.SpringWithDocker.constants.GlobalConstants.CREATE;
import static com.beneite.SpringWithDocker.constants.GlobalConstants.EMPLOYEE_ENDPOINT;
import static com.beneite.SpringWithDocker.constants.GlobalConstants.GET_EMPLOYEE;

@Tag(
        name = "CRUD REST API's for Employee resource",
        description = "We have the following API: Create Employee"
)
@RestController
@RequestMapping(EMPLOYEE_ENDPOINT)
@AllArgsConstructor
public class EmployeeController {

    private EmployeeServiceImpl employeeServiceImpl;

    @Operation(
            summary = "Create Employee API",
            description = "this API creates an Employee record"
    )
    @ApiResponse(
            responseCode = "201",
            description = "201-created, display the Employee record created"
    )
    @PostMapping(CREATE)
    public ResponseEntity<CreateEmployeeResponseDto> createUserApi(@Valid @RequestBody CreateEmployeeRequestDto createEmployeeRequestDto){
        CreateEmployeeResponseDto createEmployeeResponseDto = employeeServiceImpl.createEmployeeImplementation(createEmployeeRequestDto);
        return new ResponseEntity<>(createEmployeeResponseDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All Employee API",
            description = "this API get all Employee record"
    )
    @ApiResponse(
            responseCode = "200",
            description = "200-success, display All the employee record created"
    )
    @GetMapping(GET_EMPLOYEE)
    public ResponseEntity<List<GetEmployeeResponseDto>> getAllUsersApi(){
        List<GetEmployeeResponseDto> allUsersResponse = employeeServiceImpl.getAllEmployeeImplementation();
        return new ResponseEntity<>(allUsersResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Get User API",
            description = "this API get a Employee record by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "200-success, display the Employee record created"
    )
    @GetMapping(GET_EMPLOYEE+"/id/{id}")
    public ResponseEntity<GetEmployeeResponseDto> getUserByIdApi(@PathVariable("id") Long userId){
        GetEmployeeResponseDto getEmployeeResponseDto = employeeServiceImpl.getEmployeeImplementation(userId);
        return new ResponseEntity<>(getEmployeeResponseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Get Employee details from company email id",
            description = "this API get a Employee record by company email id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "200-success, display the Employee record"
    )
    @GetMapping(GET_EMPLOYEE+"/companyEmail/{email}")
    public ResponseEntity<GetEmployeeResponseDto> getUserByCompanyEmailIdApi(@PathVariable("email") String companyEmailId){
        GetEmployeeResponseDto getEmployeeResponseDto = employeeServiceImpl.getEmployeeByCompanyEmailId(companyEmailId);
        return new ResponseEntity<>(getEmployeeResponseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Get Employee from a specific department name",
            description = "this API get a Employee record by from a specific department"
    )
    @ApiResponse(
            responseCode = "200",
            description = "200-success, display Employee from a specific department name"
    )
    @GetMapping(GET_EMPLOYEE+"/departmentName/{departmentName}")
    public ResponseEntity<List<GetEmployeeResponseDto>> getUserByDepartmentNameApi(@PathVariable("departmentName") String departmentName){
        List<GetEmployeeResponseDto> getEmployeeResponseDto = employeeServiceImpl.getEmployeeByDepartment(departmentName);
        return new ResponseEntity<>(getEmployeeResponseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Get Employee from a specific businessUnit name",
            description = "this API get a Employee record by from a specific businessUnit"
    )
    @ApiResponse(
            responseCode = "200",
            description = "200-success, display Employee from a specific businessUnit name"
    )
    @GetMapping(GET_EMPLOYEE+"/businessUnit/{businessUnit}")
    public ResponseEntity<List<GetEmployeeResponseDto>> getUserByBusinessUnitApi(@PathVariable("businessUnit") String departmentName){
        List<GetEmployeeResponseDto> getEmployeeResponseDto = employeeServiceImpl.getEmployeeByBusinessUnit(departmentName);
        return new ResponseEntity<>(getEmployeeResponseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Get Employee from a specific band name",
            description = "this API get a Employee record by from a specific band"
    )
    @ApiResponse(
            responseCode = "200",
            description = "200-success, display Employee from a specific band name"
    )
    @GetMapping(GET_EMPLOYEE+"/band/{businessUnit}")
    public ResponseEntity<List<GetEmployeeResponseDto>> getUserByBandApi(@PathVariable("businessUnit") String departmentName){
        List<GetEmployeeResponseDto> getEmployeeResponseDto = employeeServiceImpl.getEmployeeByBand(departmentName);
        return new ResponseEntity<>(getEmployeeResponseDto, HttpStatus.OK);
    }
}
