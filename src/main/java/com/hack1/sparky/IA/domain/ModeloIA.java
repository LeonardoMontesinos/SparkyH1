package com.hack1.sparky.IA.domain;

public enum ModeloIA {
    OPENAI_GPT_4O("openai/gpt-4o"),
    DEEPSEEK_V3("deepseek/DeepSeek-V3-0324"),
    LLAMA_4_SCOUT("meta/Llama-4-Scout-17B-16E-Instruct");

    private final String nombreEnGithub;

    ModeloIA(String nombreEnGithub) {
        this.nombreEnGithub = nombreEnGithub;
    }

    public String getNombreEnGithub() {
        return nombreEnGithub;
    }
}
