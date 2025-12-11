package com.example.Mylab.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_analyse")
public class UserAnalyse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "analyse_id", nullable = false)
    private Analyse analyse;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AnalyseStatus status = AnalyseStatus.IN_PROGRESS;

    @Enumerated(EnumType.STRING)
    @Column(name = "validation_status", nullable = false)
    private ValidationStatus validationStatus = ValidationStatus.NOT_VALIDATED;



}
