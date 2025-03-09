package com.beneite.SpringWithDocker.controllerTest;

import com.beneite.SpringWithDocker.controller.UserController;
import com.beneite.SpringWithDocker.dto.requestDto.UserDto;
import com.beneite.SpringWithDocker.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class) // Ensures Mockito works
class UserControllerTest {

    @InjectMocks
    private UserController userController; // Ensures mock dependencies are injected

    @MockBean
    private UserServiceImpl userServiceImpl; // Tells Spring to mock and inject this bean

    @Test
    public void testCreateUserApi(){
        // Given: Mock Service Layer
        when(userServiceImpl.createUserImplementation(any(UserDto.class)))
                .thenReturn(buildCreateUserDto());

        // Act: Call the controller method
        ResponseEntity<UserDto> createUserResponse = userController.createUserApi(buildCreateUserDto());

        // Assert: Validate the response
        assertNotNull(createUserResponse, "Response should not be null");
        assertEquals(HttpStatus.CREATED, createUserResponse.getStatusCode());

        // asserting the response body data
        assertEquals(createUserResponse.getBody().getId(), buildCreateUserDto().getId());
        assertEquals(createUserResponse.getBody().getEmail(), buildCreateUserDto().getEmail());
        assertEquals(createUserResponse.getBody().getFirstName(), buildCreateUserDto().getFirstName());
        assertEquals(createUserResponse.getBody().getLastName(), buildCreateUserDto().getLastName());

    }

    @Test
    public void testGetUserByIdApi() {
        // Given: Mock Service Layer
        when(userServiceImpl.getUserByIdImplementation(buildGetUserByIdDto().getId()))
                .thenReturn(buildCreateUserDto());

        // Act: Call the controller method
        ResponseEntity<UserDto> getUserByIdResponse = userController.getUserByIdApi(buildGetUserByIdDto().getId());

        // Assert: Validate the response
        assertNotNull(getUserByIdResponse, "Response should not be null");
        assertEquals(HttpStatus.OK, getUserByIdResponse.getStatusCode());

        // asserting the response body data
        assertEquals(getUserByIdResponse.getBody().getId(), buildCreateUserDto().getId());
        assertEquals(getUserByIdResponse.getBody().getEmail(), buildCreateUserDto().getEmail());
        assertEquals(getUserByIdResponse.getBody().getFirstName(), buildCreateUserDto().getFirstName());
        assertEquals(getUserByIdResponse.getBody().getLastName(), buildCreateUserDto().getLastName());

    }

    @Test
    public void testGetAllUsersApi(){
        when(userServiceImpl.getAllUsersImplementation())
                .thenReturn(buildGetAllUsersDto());

        // Act: Call the controller method
        ResponseEntity<List<UserDto>> getAllUsersResponse = userController.getAllUsersApi();

        // Assert: Validate the response
        assertNotNull(getAllUsersResponse);
        assertEquals(HttpStatus.OK, getAllUsersResponse.getStatusCode());
        assertEquals(buildGetAllUsersDto().size(), getAllUsersResponse.getBody().size());
        assertNotNull(getAllUsersResponse.getBody());

        // ✅ Compare Lists Using AssertJ (Recommended)
        assertThat(getAllUsersResponse.getBody()).usingRecursiveComparison().isEqualTo(buildGetAllUsersDto());

    }

    private UserDto buildCreateUserDto(){
        return new UserDto(1L, "Ashish.Mishra@yopmail.com", "Ashish", "Mishra");
    }

    private UserDto buildGetUserByIdDto(){
        return new UserDto(10L, "Ashish.Mishra@yopmail.com", "Ashish", "Mishra");
    }

    private List<UserDto> buildGetAllUsersDto(){
        return List.of(new UserDto(10L, "Ashish.Mishra@yopmail.com", "Ashish", "Mishra"),
                new UserDto(11L, "Manish.arthore@yopmail.com", "Manish", "arthore"),
                new UserDto(1L, "Alok.Jha@yopmail.com", "Alok", "Jha")
        );

    }

}
