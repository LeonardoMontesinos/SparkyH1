package com.hack1.sparky.restriction.dto;

import com.hack1.sparky.restriction.domain.ModeloIA;

public class RestrictionResponseDTO {
    private Long id;
    private ModeloIA model;
    private int maxRequestsPerDay;
    private int maxTokensPerRequest;
}
