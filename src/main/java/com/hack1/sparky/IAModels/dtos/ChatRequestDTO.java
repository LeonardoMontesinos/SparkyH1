package com.hack1.sparky.IAModels.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class ChatRequestDTO {
    @NotBlank
    private String systemPrompt;

    @NotEmpty
    private List<@NotBlank String> userMessages;
}
