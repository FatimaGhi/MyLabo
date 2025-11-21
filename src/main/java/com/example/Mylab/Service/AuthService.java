package com.example.Mylab.Service;


import com.example.Mylab.DTO.PatientRequest;
import com.example.Mylab.DTO.PatientRequestForRegester;
import com.example.Mylab.DTO.PatientResponse;
import com.example.Mylab.DTO.UserRequest;
import com.example.Mylab.Mapper.PatientMapper;
import com.example.Mylab.Model.RoleName;
import com.example.Mylab.Model.User;
import com.example.Mylab.Repository.RoleRepo;
import com.example.Mylab.Repository.UserRepo;
import com.example.Mylab.shared.CustomResponseException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    private PasswordEncoder passwordEncoder;
    private EmailServerice emailServerice;
    private RoleRepo roleRepo;
    private UserRepo userRepo;
    private PatientService patientService;
    private PatientMapper patientMapper;
    private AuthenticationManager authenticationManager;
    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private UserDetailsServiceImp userDetailsServiceImp;



    public AuthService(UserDetailsServiceImp userDetailsServiceImp,JwtEncoder jwtEncoder,JwtDecoder jwtDecoder,AuthenticationManager authenticationManager,PatientMapper patientMapper, PatientService patientService, PasswordEncoder passwordEncoder, EmailServerice emailServerice , RoleRepo roleRepo, UserRepo userRepo){
        this.passwordEncoder= passwordEncoder;
        this.emailServerice=emailServerice;
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.patientService = patientService ;
        this.patientMapper = patientMapper ;
        this.authenticationManager =authenticationManager;
        this.jwtEncoder=jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.userDetailsServiceImp = userDetailsServiceImp;

    }

    @Transactional
    public User  SignUp(PatientRequestForRegester patientRequestForRegester){
        // ADD check is already Email in database
        if(userRepo.existsByEmail(patientRequestForRegester.getEmail())){
            throw CustomResponseException.Conflict("this Email is already used");
        }

        // add Data to User
        User user = User.builder().email(patientRequestForRegester.getEmail()).password(passwordEncoder.encode(patientRequestForRegester.getPassword())).build();
        user.setRole(roleRepo.findByRolename(RoleName.PATIENT));
        String token = UUID.randomUUID().toString();
        user.setVerified(false);

        user.setAccountCreationToken(token);
        //save user in DAtaBase
        userRepo.save(user);

        // email validation
        emailServerice.sendAccountCreationEmail(user.getEmail(),token );

        //is email valid create patient
        PatientRequest patientRequest = patientMapper.PatientRequestForRegester_TO_PatientRequest(patientRequestForRegester);

        PatientResponse patientResponse = patientService.CreatePatient(patientRequest);

//         ADD id patient to user
        user.setIdInfoUser(patientResponse.getId());

        return user ;

    }


    public Map<String,String> SingIn(UserRequest userRequest){
        Map<String,String>  ID_token = new HashMap<>();
        Instant instant = Instant.now();


        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getEmail(),userRequest.getPassword()
                ));

        // création des ID Token
        //1 -Acess token

        JwtClaimsSet jwtClaimsSet_acessToken = JwtClaimsSet.builder()
                .subject(authenticate.getName())
                .issuer("MyLabo")
                .issuedAt(instant)
                .expiresAt(instant.plus(2, ChronoUnit.MINUTES))
                .claim("scope", authenticate.getAuthorities())
                .build();
        System.out.println("^^^^^^^^^^^^ : "+ jwtClaimsSet_acessToken.getClaims());

        String Access_token = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet_acessToken)).getTokenValue();

        // refresh token

        JwtClaimsSet jwtClaimsSet_refreshToken = JwtClaimsSet.builder()
                .subject(authenticate.getName())
                .issuer("MyLabo")
                .issuedAt(instant)
                .expiresAt(instant.plus(15, ChronoUnit.MINUTES))
                .build();

        String Refresh_token = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet_refreshToken)).getTokenValue();

        ID_token.put("Access_Token",Access_token);
        ID_token.put("Refresh_Token",Refresh_token);

        return ID_token;
    }

    public Map<String,String>  refresh(String refreshToken){
        Map<String,String> ID_Token= new HashMap<>();
        String email = jwtDecoder.decode(refreshToken).getSubject();

        if (refreshToken == null){
           throw CustomResponseException.BadRequest(" Refresh Token is requered ");
        }

        UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(email);
        Instant instant = Instant.now();
        // create new  refresh Token
        JwtClaimsSet jwtClaimsSet_refreshToken = JwtClaimsSet.builder()
                .subject(userDetails.getUsername())
                .issuer("MyLabo")
                .issuedAt(instant)
                .expiresAt(instant.plus(15, ChronoUnit.MINUTES))
                .build();

        String Refresh_token = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet_refreshToken)).getTokenValue();

        JwtClaimsSet jwtClaimsSet_acessToken = JwtClaimsSet.builder()
                .subject(userDetails.getUsername())
                .issuer("Mylabo")
                .issuedAt(instant)
                .expiresAt(instant.plus(2, ChronoUnit.MINUTES))
                .claim("scope", userDetails.getAuthorities())
                .build();

        String Access_token = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet_acessToken)).getTokenValue();

        ID_Token.put("Access_Token",Access_token);
        ID_Token.put("Refresh_Token",Refresh_token);
        return ID_Token;

    }
    // for verified account
    public void verify(String token){

        if(token == null){
            throw  CustomResponseException.BadRequest(" token is required");
        }

        User user = userRepo.findByAccountCreationToken(token)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound("don't found this Token "));


        if (user.isVerified()) {
            throw CustomResponseException.Conflict(" this account is already verified");
        }

        user.setVerified(true);

        user.setAccountCreationToken("");

        userRepo.save(user);
    }



}
