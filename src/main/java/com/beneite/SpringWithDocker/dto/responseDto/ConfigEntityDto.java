package com.beneite.SpringWithDocker.dto.responseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "Config DTO model information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigEntityDto {

    @Schema(
            description = "Config Id"
    )
    private Long id;

    @Schema(
            description = "Config key name"
    )
    private String key;

    @Schema(
            description = "Config value"
    )
    private String values; // store as JSON string
}
