package com.hack1.sparky.company.dto;

import com.hack1.sparky.user.domain.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCompanyDTO {
    @NotBlank
    @Size(max = 100)
    private String name;

    @Valid
    @NotNull
    private User admin;
}

