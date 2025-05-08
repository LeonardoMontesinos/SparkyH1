package com.hack1.sparky.company.domain;

import com.hack1.sparky.restriction.domain.ModeloIA;
import com.hack1.sparky.restriction.domain.Restriction;
import com.hack1.sparky.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "default_model", nullable = false, length = 100)
    private ModeloIA defaultModel;

    @Column(nullable = false)
    private boolean active = true;

    @OneToOne
    @JoinColumn(name = "admin_id", unique = true)
    private User admin;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Restriction> restrictions = new ArrayList<>();
}