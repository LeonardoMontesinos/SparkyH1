package com.hack1.sparky.requestLog.dto;

import com.hack1.sparky.restriction.domain.ModeloIA;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestLogResponseDTO {
    private Long id;
    private LocalDateTime timestamp;
    private ModeloIA model;
    private String prompt;
    private String response;
    private int tokensUsed;
    private String endpoint;
}
