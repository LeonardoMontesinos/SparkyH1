package com.hack1.sparky.Usuario.domain;

import com.hack1.sparky.Empresa.domain.Empresa;
import com.hack1.sparky.Limite.domain.Limite;
import com.hack1.sparky.Solicitud.domain.Solicitud;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombreUsuario;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @ManyToOne
    private Empresa empresa;

    @OneToMany(mappedBy = "usuario")
    private List<Limite> limites;

    @OneToMany(mappedBy = "usuario")
    private List<Solicitud> solicitudes;
}
