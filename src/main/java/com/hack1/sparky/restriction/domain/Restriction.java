package com.hack1.sparky.restriction.domain;
import com.hack1.sparky.company.domain.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Table(name = "restrictions")
@Data
public class Restriction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ModeloIA model;

    @Min(1)
    @Column(nullable = false)
    private int maxRequestsPerDay;

    @Min(1)
    @Column(nullable = false)
    private int maxTokensPerRequest;
}