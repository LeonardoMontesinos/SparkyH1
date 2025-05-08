package com.hack1.sparky.restriction.dto;

import jakarta.validation.constraints.Min;

public class UpdateRestrictionDTO {
    @Min(1)
    private int maxRequestsPerDay;

    @Min(1)
    private int maxTokensPerRequest;
}
