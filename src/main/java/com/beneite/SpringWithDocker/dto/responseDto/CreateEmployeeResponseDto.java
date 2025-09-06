package com.beneite.SpringWithDocker.dto.responseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Schema(
        description = "Create Employee Response Dto model information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEmployeeResponseDto {

    @Schema(description = "Generated employee ID")
    private Long id;  // generated employeeId

    @Schema(description = "First name of the employee")
    private String firstName;

    @Schema(description = "Last name of the employee")
    private String lastName;

    @Schema(description = "Company email of the employee")
    private String companyEmail;

    @Schema(description = "Personal email of the employee")
    private String personalEmail;

    @Schema(description = "Date of joining")
    private LocalDate dateOfJoining;

    @Schema(description = "Department of the employee")
    private String department;

    @Schema(description = "Business unit of the employee")
    private String businessUnit;

    @Schema(description = "Designation of the employee")
    private String designation;

    @Schema(description = "Employee band (L1 - L10)")
    private String band;

    @Schema(description = "Custom response message")
    private String message;

}
