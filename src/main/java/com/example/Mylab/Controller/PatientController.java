package com.example.Mylab.Controller;


import com.example.Mylab.DTO.PatientRequest;
import com.example.Mylab.DTO.PatientResponse;
import com.example.Mylab.Service.PatientService;
import com.example.Mylab.shared.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Patients")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<PatientResponse>> getOnePatient(@RequestParam UUID id) {
        PatientResponse patientResponse = patientService.getOnePatient(id);
        return new ResponseEntity<GlobalResponse<PatientResponse>>(new GlobalResponse<PatientResponse>(patientResponse), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<PatientResponse>>> getOnePatient() {
        List<PatientResponse> patientResponse = patientService.getAllPAtients();
        return new ResponseEntity<GlobalResponse<List<PatientResponse>>>(new GlobalResponse<List<PatientResponse>>(patientResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<String>> deletePatient(@RequestParam UUID id) {
        patientService.DeletePatient(id);
        return new ResponseEntity<GlobalResponse<String>>(new GlobalResponse<String>(id + " is delete "), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<PatientResponse>> updatePatient(@RequestParam UUID id, @RequestBody PatientRequest patientRequest) {
        PatientResponse patientResponse = patientService.UpdatePatient(patientRequest,id);
        return new ResponseEntity<GlobalResponse<PatientResponse>>(new GlobalResponse<PatientResponse>(patientResponse), HttpStatus.OK);
    }

}
