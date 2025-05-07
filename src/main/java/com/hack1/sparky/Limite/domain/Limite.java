package com.hack1.sparky.Limite.domain;

import com.hack1.sparky.IA.domain.ModeloIA;
import com.hack1.sparky.Usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "limites")
public class Limite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ModeloIA modelo;

    private Integer maxSolicitudes;
    private Integer maxTokens;
    private Instant inicioVentana;

    @ManyToOne
    private Usuario usuario;
}
