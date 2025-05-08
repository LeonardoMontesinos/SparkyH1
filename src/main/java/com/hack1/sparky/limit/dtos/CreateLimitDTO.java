package com.hack1.sparky.limit.dtos;

import com.hack1.sparky.restriction.domain.ModeloIA;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CreateLimitDTO {
    @NotNull
    private ModeloIA model;

    @Min(1)
    private int maxRequests;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
