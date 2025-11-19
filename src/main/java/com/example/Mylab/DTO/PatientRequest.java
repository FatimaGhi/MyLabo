package com.example.Mylab.DTO;

import com.example.Mylab.Model.Gender;
import jakarta.validation.constraints.*;

public class PatientRequest {
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;


    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;


    @NotBlank(message = "Date of birth is required")
    // Format: yyyy-MM-dd
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in the format yyyy-MM-dd")
    private String dateOfBirth;


    @NotBlank(message = "National ID number is required")
    @Pattern(regexp = "\\d{8,20}", message = "National ID number must be numeric and between 8 and 20 digits")
    private String nationalIdNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private  String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    private String  password ;

    @NotNull(message = "Gender is required")
    private Gender gender;  // Enum: MALE, FEMALE

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be numeric and can include country code")
    private String phone;

}
