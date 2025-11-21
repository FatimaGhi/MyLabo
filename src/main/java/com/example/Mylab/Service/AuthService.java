package com.example.Mylab.Service;


import com.example.Mylab.DTO.PatientRequest;
import com.example.Mylab.DTO.PatientRequestForRegester;
import com.example.Mylab.DTO.PatientResponse;
import com.example.Mylab.Mapper.PatientMapper;
import com.example.Mylab.Model.RoleName;
import com.example.Mylab.Model.User;
import com.example.Mylab.Repository.RoleRepo;
import com.example.Mylab.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    private PasswordEncoder passwordEncoder;
    private EmailServerice emailServerice;
    private RoleRepo roleRepo;
    private UserRepo userRepo;
    private PatientService patientService;
    private PatientMapper patientMapper;


    public AuthService(PatientMapper patientMapper, PatientService patientService, PasswordEncoder passwordEncoder, EmailServerice emailServerice , RoleRepo roleRepo, UserRepo userRepo){
        this.passwordEncoder= passwordEncoder;
        this.emailServerice=emailServerice;
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.patientService = patientService ;
        this.patientMapper = patientMapper ;

    }

    @Transactional
    public User  SignUp(PatientRequestForRegester patientRequestForRegester){
        // ADD check is already Email in database
//        **************************************************************

        // add Data to User
        User user = User.builder().email(patientRequestForRegester.getEmail()).password(passwordEncoder.encode(patientRequestForRegester.getPassword())).build();
        user.setRole(roleRepo.findByRolename(RoleName.PATIENT));
        String token = UUID.randomUUID().toString();
        user.setVerified(false);

        user.setAccountCreationToken(token);
        System.out.println(" &&&&&&&&&&&&&&&&&&&&&&&&&&& patientRequestForRegester :   "+patientRequestForRegester.getDateOfBirth());

        //save user in DAtaBase
        userRepo.save(user);

        // email validation
        emailServerice.sendAccountCreationEmail(user.getEmail(),token );

        //is email valid create patient
        PatientRequest patientRequest = patientMapper.PatientRequestForRegester_TO_PatientRequest(patientRequestForRegester);

        System.out.println(" &&&&&&&&&&&&&&&&&&&&&&&&&&& patientRequest :   "+patientRequest.getDateOfBirth());


        PatientResponse patientResponse = patientService.CreatePatient(patientRequest);

//         ADD id patient to user
        user.setIdInfoUser(patientResponse.getId());

        return user ;

    }
}
