package com.example.Mylab.Mapper;

import com.example.Mylab.DTO.PatientRequest;
import com.example.Mylab.DTO.PatientRequestForRegester;
import com.example.Mylab.DTO.PatientResponse;
import com.example.Mylab.Model.Patient;
import com.example.Mylab.shared.CustomResponseException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PatientMapper {

    public Patient PatientRequestForRegester_TO_Patient(PatientRequestForRegester requestForRegester){
        Patient patient = new Patient();
        BeanUtils.copyProperties(requestForRegester,patient);
        // Handle dateOfBirth conversion
        if(requestForRegester.getDateOfBirth() == null){
            throw CustomResponseException.BadRequest("Date of birth is required");
        }
        patient.setDateOfBirth(requestForRegester.getDateOfBirth());
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
        patientResponse.setDateOfBirth(patient.getDateOfBirth());
        return patientResponse;


    }

    public PatientRequest PatientRequestForRegester_TO_PatientRequest(PatientRequestForRegester requestForRegester){
        PatientRequest patientRequest = new PatientRequest();
        BeanUtils.copyProperties(requestForRegester,patientRequest);
        if (patientRequest.getDateOfBirth() == null) {
            patientRequest.setDateOfBirth(requestForRegester.getDateOfBirth());
        }

        return patientRequest;
    }

}
