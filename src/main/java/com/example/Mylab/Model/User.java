package com.example.Mylab.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "Email",nullable = false)
    private  String email;
    @Column(name = "password",nullable = false)
    private String  password ;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "idInfoUser",unique = true)
    private UUID idInfoUser;

    @Column(name = "isVerified",nullable = false)
    private boolean isVerified = false;

    @Column(name = "accountCreationToken",nullable = false)
    private String accountCreationToken;

}
