package com.hack1.sparky.Restriccion.domain;

import com.hack1.sparky.Empresa.domain.Empresa;
import com.hack1.sparky.IA.domain.ModeloIA;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;

@Entity
@Data
@Table(name = "restricciones")
public class Restriccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ModeloIA modelo;

    private Integer maxSolicitudes;
    private Integer maxTokens;
    private Duration ventanaTiempo;

    @ManyToOne
    private Empresa empresa;
}
