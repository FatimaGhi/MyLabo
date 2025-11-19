package com.example.Mylab.Model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

public class LabTechnician {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "firstName",nullable = false)
    private String firstName;
    @Column(name = "lastName",nullable = false)
    private String lastName;
    @Column(name = "dateOfBirth",nullable = false)
    private String dateOfBirth;
    @Column(name = "nationalIdNumber",nullable = false, unique = true)
    private String nationalIdNumber;
    @Column(name = "Gender",nullable = false)
    private Gender gender;
    @Column(name = "phone",nullable = false, unique = true)
    private String phone;
}
