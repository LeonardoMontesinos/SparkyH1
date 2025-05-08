package com.hack1.sparky.requestLog.dto;

import com.hack1.sparky.restriction.domain.ModeloIA;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRequestLogDTO {
    @NotNull
    private ModeloIA model;

    @NotBlank
    private String prompt;

    @NotBlank
    private String response;

    @Min(0)
    private int tokensUsed;

    @NotBlank
    private String endpoint;
}
