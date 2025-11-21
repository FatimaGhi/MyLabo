package com.example.Mylab.Service;

import com.example.Mylab.Model.User;
import com.example.Mylab.Repository.UserRepo;
import com.example.Mylab.shared.CustomResponseException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {


    private UserRepo userRepo;
    UserDetailsServiceImp(UserRepo userRepo){
        this.userRepo=userRepo;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

   User userDB = userRepo.findByEmail(email).orElseThrow(() -> CustomResponseException.BadCredentials());

    return org.springframework.security.core.userdetails.User
            .builder()
            .username(userDB.getUsername())
            .password(userDB.getPassword())
            .authorities(new SimpleGrantedAuthority(userDB.getRole().getRolename().toString()))
            .build();
    }
}
