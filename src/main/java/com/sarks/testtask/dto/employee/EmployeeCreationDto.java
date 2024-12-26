package com.sarks.testtask.dto.employee;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCreationDto {
    @Size(min=4, max=20)
    private String username;
    @Size(min=6)
    private String password;
}
