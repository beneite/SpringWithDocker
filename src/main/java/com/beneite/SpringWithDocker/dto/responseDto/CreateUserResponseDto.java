package com.beneite.SpringWithDocker.dto.responseDto;

import com.beneite.SpringWithDocker.dto.requestDto.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "Create User Response Dto model information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String message;

    public CreateUserResponseDto(UserDto user, String message) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.message = message;
    }
}
