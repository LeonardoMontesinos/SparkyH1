package com.hack1.sparky.Empresa.domain;

import com.hack1.sparky.IA.domain.ModeloIA;
import com.hack1.sparky.Restriccion.domain.Restriccion;
import com.hack1.sparky.Usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "empresas")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Empresa {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String ruc;

    private LocalDate fechaAfiliacion;

    private boolean activa;

    @Enumerated(EnumType.STRING)
    private ModeloIA modeloIA; // Enum con modelos permitidos

    @OneToOne
    private Usuario administrador;

    @OneToMany(mappedBy = "empresa")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "empresa")
    private List<Restriccion> restricciones;

    public Empresa(String nombre, String ruc, LocalDate fechaAfiliacion, boolean activa, ModeloIA modeloIA) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.fechaAfiliacion = fechaAfiliacion;
        this.activa = activa;
        this.modeloIA = modeloIA;
    }
}
