package com.example.Mylab.DTO;

import com.example.Mylab.Model.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientRequest {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;


    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;


    @NotNull(message = "Date of birth is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;


    @NotBlank(message = "National ID number is required")
    @Pattern(regexp = "\\d{8,20}", message = "National ID number must be numeric and between 8 and 20 digits")
    private String nationalIdNumber;


    @NotNull(message = "Gender is required")
    @Enumerated(EnumType.STRING)
    private Gender gender;  // Enum: MALE, FEMALE

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be numeric and can include country code")
    private String phone;
}
