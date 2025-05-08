package com.hack1.sparky.restriction.domain;


import lombok.Getter;
import java.util.Arrays;

@Getter
public enum ModeloIA {
    OPENAI_GPT_4O("openai/gpt-4o"),
    DEEPSEEK_V3("deepseek/DeepSeek-V3-0324"),
    LLAMA_4_SCOUT("meta/Llama-4-Scout-17B-16E-Instruct");

    private final String path;

    ModeloIA(String path) {
        this.path = path;
    }

    public static ModeloIA fromPath(String path) {
        return Arrays.stream(values())
                .filter(m -> m.path.equalsIgnoreCase(path))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ModeloIA no reconocido: " + path));
    }
}
