package com.hack1.sparky.Solicitud.domain;

import com.hack1.sparky.Empresa.domain.Empresa;
import com.hack1.sparky.IA.domain.ModeloIA;
import com.hack1.sparky.Usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String consulta;
    private String respuesta;
    private Integer tokensConsumidos;
    private Instant fechaHora;
    private String nombreArchivo; // opcional

    @Enumerated(EnumType.STRING)
    private ModeloIA modelo;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Empresa empresa;

    private boolean fueError;
}
