package com.hack1.sparky.limit.dtos;

import com.hack1.sparky.restriction.domain.ModeloIA;

import java.time.LocalDate;

public class LimitResponseDTO {
    private Long id;
    private ModeloIA model;
    private int maxRequests;
    private LocalDate startDate;
    private LocalDate endDate;
}
