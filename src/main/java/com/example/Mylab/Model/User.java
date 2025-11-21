package com.example.Mylab.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.getRolename().toString()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
