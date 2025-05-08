package com.hack1.sparky.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCompanyDTO {
    @NotBlank
    @Size(max = 100)
    private String name;
}
