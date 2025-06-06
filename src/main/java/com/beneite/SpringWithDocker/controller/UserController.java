package com.beneite.SpringWithDocker.controller;

import com.beneite.SpringWithDocker.dto.requestDto.UserDto;
import com.beneite.SpringWithDocker.dto.requestDto.UserIdRequestDto;
import com.beneite.SpringWithDocker.dto.responseDto.UserIdResponseDto;
import com.beneite.SpringWithDocker.service.implementation.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "CRUD REST API's for user resource",
        description = "We have the following API: Create user, update user, delete user, read user details"
)
@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private UserServiceImpl userServiceImpl;

    // Api to create user, end point: http://localhost:8080/api/user/create
    @Operation(
            summary = "Create User API",
            description = "this API creates a student record"
    )
    @ApiResponse(
            responseCode = "201",
            description = "201-created, display the student record created"
    )
    @PostMapping("create")
    public ResponseEntity<UserDto> createUserApi(@Valid @RequestBody UserDto userEntity){
        UserDto savedUser = userServiceImpl.createUserImplementation(userEntity);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get User API",
            description = "this API get a student record by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "200-success, display the student record created"
    )
    // Api to get the user by id, end point: http://localhost:8080/api/user/getUserById/2
    @GetMapping("getUserById/{id}")
    public ResponseEntity<UserDto> getUserByIdApi(@PathVariable("id") Long userId){
        UserDto getUser = userServiceImpl.getUserByIdImplementation(userId);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }

    @Operation(
            summary = "Get All User API",
            description = "this API get all student record"
    )
    @ApiResponse(
            responseCode = "200",
            description = "200-success, display All the student record created"
    )
    // Api to get all the users data, endpoint: http://localhost:8080/api/user/getAllUser
    @GetMapping("getAllUser")
    public ResponseEntity<List<UserDto>> getAllUsersApi(){
        List<UserDto> allUsersResponse = userServiceImpl.getAllUsersImplementation();
        return new ResponseEntity<>(allUsersResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Update User API",
            description = "this API update a student record by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "200-success, display the student record updated"
    )
    // Api to update the users data, endpoint: http://localhost:8080/api/user/update/2
    @PutMapping("update/{id}")
    public ResponseEntity<UserDto> updateUserDetailsApi(@PathVariable("id") Long userId,@Valid @RequestBody UserDto userEntity){
        userEntity.setId(userId);
        UserDto updatedUser = userServiceImpl.updateUserImplementation(userEntity);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(
            summary = "delete User API",
            description = "this API delete a student record by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "200-success, display the student record deleted by id"
    )
    // Api to delete the users data, endpoint: http://localhost:8080/api/user/delete/2
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUserApi(@PathVariable("id") Long userId){
        userServiceImpl.deleteUserImplementation(userId);
        return new ResponseEntity<>("User with id:"+userId+" Deleted", HttpStatus.OK);
    }

    @Operation(
            summary = "Get User's Email from User id",
            description = "this Get User's Email from User id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "200-success, display All email id of the user"
    )
    @PostMapping("getEmailFromId")
    public ResponseEntity<UserIdResponseDto> getUserEmailFromUserId(@Valid @RequestBody UserIdRequestDto userIdRequestDto){
        UserIdResponseDto userIdResponseDto = userServiceImpl.getUserEmailFromIdImplementation(userIdRequestDto);
        return new ResponseEntity<>(userIdResponseDto, HttpStatus.OK);
    }

}
