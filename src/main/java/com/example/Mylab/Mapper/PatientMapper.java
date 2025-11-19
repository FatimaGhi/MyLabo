package com.example.Mylab.Mapper;

import com.example.Mylab.DTO.PatientRequest;
import com.example.Mylab.DTO.PatientRequestForRegester;
import com.example.Mylab.DTO.PatientResponse;
import com.example.Mylab.Model.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PatientMapper {


    public Patient PatientRequestForRegester_TO_Patient(PatientRequestForRegester requestForRegester){
        Patient patient = new Patient();
        BeanUtils.copyProperties(requestForRegester,patient);
        return patient;
    }

    public Patient  PatientRequest_To_Patient(PatientRequest patientRequest){
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientRequest,patient);
        return patient;
    }

    public PatientResponse Patient_To_PatientResponse(Patient patient){
        PatientResponse patientResponse = new PatientResponse();
        BeanUtils.copyProperties(patient,patientResponse);
        return patientResponse;
    }



}
