package com.hack1.sparky.Empresa.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EmpresaDTO {
    private String nombre;
    private String ruc;
    private LocalDate fechaAfiliacion;
    private boolean activa;
    private String modeloIA; // Nombre del modelo IA: openai/gpt-4o, etc.
}
