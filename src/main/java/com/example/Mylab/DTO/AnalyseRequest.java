package com.example.Mylab.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnalyseRequest {

    @NotBlank(message = "Name is required")
    private String nom;

    @NotNull(message = "Price is required")
    private Double prix;

    @NotBlank(message = "Normal value is required")
    private String valeurNormale;

    @NotBlank(message = "Abnormal value is required")
    private String Abnormal;

    private String description;
}
