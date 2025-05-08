package com.hack1.sparky.company.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyResponseDTO {
    private Long id;
    private String name;
    private boolean active;
    private AdminUserDTO admin;
}
