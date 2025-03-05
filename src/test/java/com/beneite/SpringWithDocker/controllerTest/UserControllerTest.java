package com.beneite.SpringWithDocker.controllerTest;

import com.beneite.SpringWithDocker.controller.UserController;
import com.beneite.SpringWithDocker.dto.UserDto;
import com.beneite.SpringWithDocker.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    }

    private UserDto buildCreateUserDto(){
        return new UserDto(1L, "Ashish.Mishra@yopmail.com", "Ashish", "Mishra");
    }

}
