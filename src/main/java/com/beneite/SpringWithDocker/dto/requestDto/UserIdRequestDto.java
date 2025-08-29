package com.beneite.SpringWithDocker.dto.requestDto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "User ID request DTO model information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserIdRequestDto {

    @NotNull(message = "User ID cannot be null")
    @Schema(
            description = "User ID"
    )
    private Long userId;

}
