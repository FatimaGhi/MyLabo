package com.example.Mylab.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "analyses")
public class Analyse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nom; // Test name

    @Column(nullable = false)
    private Double prix; // Price in MAD

    @Column(nullable = false)
    private String valeurNormale; // Normal range

    @Column(nullable = false)
    private String Abnormal; // Abnormal range

    @Column(length = 1000)
    private String description;
}
