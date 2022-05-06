package com.lokischiffer.marketapp.logic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @Email (message = "Only valid emails allowed")
    @NotNull (message = "Parameter should not be null")
    @NotEmpty (message = "Email is required")
    private String email;

    @Size (max = 50, message = "Name out of size")
    @NotNull (message = "Parameter should not be null")
    @NotEmpty (message = "Complete name is required")
    private String name;

    @Size (min = 8, message = "Password is too short")
    @Size (max = 20, message = "Password is too large")
    @NotNull (message = "Parameter should not be null")
    private String password;

}
