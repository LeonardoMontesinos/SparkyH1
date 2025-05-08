package com.hack1.sparky.requestLog.domain;

import com.hack1.sparky.restriction.domain.ModeloIA;
import com.hack1.sparky.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "request_logs")
public class RequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ModeloIA model;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String prompt;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String response;

    @Min(0)
    @Column(nullable = false)
    private int tokensUsed;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String endpoint;
}
