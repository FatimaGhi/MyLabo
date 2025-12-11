package com.example.Mylab;

import com.example.Mylab.Controller.PatientController;
import com.example.Mylab.DTO.PatientResponse;
import com.example.Mylab.Model.Gender;
import com.example.Mylab.Model.Patient;
import com.example.Mylab.Service.AuthService;
import com.example.Mylab.Service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;



@WebMvcTest(PatientController.class)
public class PatientControllerTest {
    @MockitoBean
    private PatientService patientService;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;




    public  PatientControllerTest( ) {

    }

    @Test
    void getAllPatients() throws Exception{
        List<PatientResponse> patientList = List.of(new PatientResponse(UUID.randomUUID(),
                "Ahmed",
                "El Amrani",
                LocalDate.of(1995, 3, 12),
                "JX452189",
                Gender.MALE,
                "0612345678"), new PatientResponse( UUID.randomUUID(),
                "Fatima",
                "Bennani",
                LocalDate.of(2000, 8, 25),
                "HH982314",
                Gender.FEMALE,
                "0678123456"));

        when(patientService.getAllPAtients()).thenReturn(patientList);


        mockMvc.perform(get("/Patients")
                        .with(user("admin").roles("USER")))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
