package com.example.Mylab.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PATIENT")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "firstName",nullable = false)
    private String firstName;
    @Column(name = "lastName",nullable = false)
    private String lastName;
    @Column(name = "dateOfBirth",nullable = false)
    private LocalDate dateOfBirth;
    @Column(name = "nationalIdNumber",nullable = false, unique = true)
    private String nationalIdNumber;
    @Column(name = "Gender",nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "phone",nullable = false, unique = true)
    private String phone;



}
