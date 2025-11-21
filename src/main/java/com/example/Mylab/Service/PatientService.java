package com.example.Mylab.Service;


import com.example.Mylab.DTO.PatientRequest;
import com.example.Mylab.DTO.PatientResponse;
import com.example.Mylab.Mapper.PatientMapper;
import com.example.Mylab.Model.Patient;
import com.example.Mylab.Model.User;
import com.example.Mylab.Repository.PatientRepo;
import com.example.Mylab.Repository.UserRepo;
import com.example.Mylab.shared.CustomResponseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private PatientRepo patientRepo;
    private PatientMapper patientMapper;
    private UserRepo userRepo;

    public PatientService(UserRepo userRepo,PatientRepo patientRepo,PatientMapper patientMapper){
        this.patientRepo = patientRepo;
        this.patientMapper =patientMapper;
        this.userRepo = userRepo;
    }

    public PatientResponse CreatePatient(PatientRequest patientRequest){

        Patient patient = patientMapper.PatientRequest_To_Patient(patientRequest);
        patient.setDateOfBirth(patientRequest.getDateOfBirth());
        Patient patientSave = patientRepo.save(patient);

        return patientMapper.Patient_To_PatientResponse(patientSave);
    }


    public PatientResponse getOnePatient(UUID id ){
        Patient patient = patientRepo.findById(id).orElseThrow( ()-> CustomResponseException.ResourceNotFound(id +" not found "));
        return patientMapper.Patient_To_PatientResponse(patient);
    }

    public List<PatientResponse> getAllPAtients(){
        List<PatientResponse> patientsResponse = new ArrayList<>();
        List<Patient> patients = patientRepo.findAll();

        for(Patient patient : patients){
            PatientResponse patientResponse = patientMapper.Patient_To_PatientResponse(patient);
            patientsResponse.add(patientResponse);
        }
        return patientsResponse;

    }


    public void DeletePatient(UUID id){
        Patient patient = patientRepo.findById(id).orElseThrow( ()-> CustomResponseException.ResourceNotFound(id +" not found "));
        User user = userRepo.findByidInfoUser(patient.getId()).orElseThrow(() -> CustomResponseException.ResourceNotFound(id + " don't exist in Users"));
        userRepo.delete(user);
        patientRepo.delete(patient);
    }


    public PatientResponse UpdatePatient(PatientRequest patientRequest,UUID id){
        Patient patient = patientRepo.findById(id).orElseThrow( ()-> CustomResponseException.ResourceNotFound(id +" not found "));
        if(!patientRequest.getFirstName().isEmpty()){
            patient.setFirstName(patientRequest.getFirstName());
        }
        if(!patientRequest.getLastName().isEmpty()){
            patient.setLastName(patientRequest.getLastName());
        }
        if(!patientRequest.getPhone().isEmpty()){
            patient.setPhone(patientRequest.getPhone());
        }
        if(patientRequest.getGender() != null ){
           patient.setGender(patientRequest.getGender());
        }
        if(patientRequest.getNationalIdNumber() != null){
            patient.setNationalIdNumber(patient.getNationalIdNumber());
        }
        if(patientRequest.getDateOfBirth() != null){
             patient.setDateOfBirth(patientRequest.getDateOfBirth());
        }

         patientRepo.save(patient);
        return  patientMapper.Patient_To_PatientResponse(patient);

    }


}
