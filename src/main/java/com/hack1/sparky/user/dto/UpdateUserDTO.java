package com.hack1.sparky.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UpdateUserDTO {
    @NotBlank
    @Email
    private String email;

    private boolean active;
}
