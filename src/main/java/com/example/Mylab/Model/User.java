package com.example.Mylab.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "Email",nullable = false)
    private  String email;
    @Column(name = "password",nullable = false)
    private String  password ;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "idInfoUser",nullable = false,unique = true)
    private UUID idInfoUser;

}
