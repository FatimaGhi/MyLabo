package com.example.Mylab.Controller;


import com.example.Mylab.DTO.PatientRequestForRegester;
import com.example.Mylab.DTO.UserRequest;
import com.example.Mylab.Model.User;
import com.example.Mylab.Service.AuthService;
import com.example.Mylab.shared.CustomResponseException;
import com.example.Mylab.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.net.http.HttpRequest;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;

    }

    @PostMapping("/SignUp")
    public ResponseEntity<GlobalResponse<User>> signUp(@Valid @RequestBody  PatientRequestForRegester patientRequestForRegester){
       User user = authService.SignUp(patientRequestForRegester);

       return new ResponseEntity<GlobalResponse<User>>(new GlobalResponse<User>(user), HttpStatus.CREATED);

    }
    @GetMapping("/verify")
    public ResponseEntity<GlobalResponse<String>> verifyAccount(@RequestBody String token) {
        try {
            authService.verify(token);
            return new ResponseEntity<>(new GlobalResponse<>(" your Account is verfied "), HttpStatus.OK);
        } catch (CustomResponseException e) {
            return new ResponseEntity<>(new GlobalResponse<>(e.getMessage()),HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping("/SignIn")
    public ResponseEntity<GlobalResponse<Map<String,String>>> signIn(@Valid @RequestBody UserRequest userRequest){
        Map<String,String> token = authService.SingIn(userRequest);
        return new ResponseEntity<GlobalResponse<Map<String,String>>>(new GlobalResponse<Map<String,String>>(token), HttpStatus.OK);

    }
    @PostMapping("/Refresh")
    public ResponseEntity<GlobalResponse<Map<String,String>>> refrech(@RequestHeader("Authorization") String tokenR){
        if (tokenR.startsWith("Bearer ")) {
            tokenR = tokenR.substring(7);
        }
        Map<String,String> token = authService.refresh(tokenR);
        return new ResponseEntity<GlobalResponse<Map<String,String>>>(new GlobalResponse<Map<String,String>>(token), HttpStatus.OK);

    }

}
