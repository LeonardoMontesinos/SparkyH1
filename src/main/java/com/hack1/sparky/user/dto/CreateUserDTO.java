package com.hack1.sparky.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min =8)
    private String password;
}
