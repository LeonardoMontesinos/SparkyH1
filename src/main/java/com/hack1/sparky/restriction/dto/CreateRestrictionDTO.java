package com.hack1.sparky.restriction.dto;

import com.hack1.sparky.restriction.domain.ModeloIA;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRestrictionDTO {
    @NotNull
    private ModeloIA model;

    @Min(1)
    private int maxRequestsPerDay;

    @Min(1)
    private int maxTokensPerRequest;
}
