package com.example.Mylab.DTO;

import com.example.Mylab.Model.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String nationalIdNumber;
    private Gender gender;
    private String phone;
}
