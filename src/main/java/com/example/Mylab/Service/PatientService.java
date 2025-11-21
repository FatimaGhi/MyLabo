package com.example.Mylab.Service;


import com.example.Mylab.DTO.PatientRequest;
import com.example.Mylab.DTO.PatientResponse;
import com.example.Mylab.Mapper.PatientMapper;
import com.example.Mylab.Model.Patient;
import com.example.Mylab.Model.Role;
import com.example.Mylab.Repository.PatientRepo;
import com.example.Mylab.Repository.RoleRepo;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private PatientRepo patientRepo;
    private RoleRepo roleRepo;
    private PatientMapper patientMapper;

    public PatientService(PatientRepo patientRepo,RoleRepo roleRepo,PatientMapper patientMapper){
        this.patientRepo = patientRepo;
        this.roleRepo = roleRepo;
        this.patientMapper =patientMapper;
    }

    public PatientResponse CreatePatient(PatientRequest patientRequest){

        Patient patient = patientMapper.PatientRequest_To_Patient(patientRequest);

        patient.setDateOfBirth(patientRequest.getDateOfBirth());
        System.out.println(" &&&&&&&&&&&&&&&&&&&&&&&&&&&  "+patient.getDateOfBirth());

        Patient patientSave = patientRepo.save(patient);

        return patientMapper.Patient_To_PatientResponse(patientSave);
    }


}
