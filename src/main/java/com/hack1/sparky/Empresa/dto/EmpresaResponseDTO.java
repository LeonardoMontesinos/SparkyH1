package com.hack1.sparky.Empresa.dto;

import lombok.Data;

@Data
public class EmpresaResponseDTO {
    private Long id;
    private String nombre;
    private String ruc;
    private boolean activa;
    private String modeloIA;
}
