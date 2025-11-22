package com.example.Mylab.Service;

import com.example.Mylab.DTO.AnalyseRequest;
import com.example.Mylab.Repository.AnalyseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private AnalyseService analyseService;
    private AnalyseRepo analyseRepo;
    public DataLoader(AnalyseService analyseService,AnalyseRepo analyseRepo){
        this.analyseService = analyseService;
        this.analyseRepo = analyseRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (analyseRepo.count() == 0) {
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "Glycémie à jeun", 50.0, "70-100 mg/dL", "<70 or >100",
                    "Measures blood sugar levels after fasting. Helps diagnose diabetes and monitor treatment."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "Cholestérol total", 70.0, "<200 mg/dL", "≥200 mg/dL",
                    "Measures total cholesterol in blood. High levels increase risk of heart disease."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "HDL Cholestérol", 60.0, ">40 mg/dL", "≤40 mg/dL",
                    "Good cholesterol that protects the heart. Low levels increase heart disease risk."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "LDL Cholestérol", 60.0, "<100 mg/dL", "≥130 mg/dL",
                    "Bad cholesterol. High levels increase risk of atherosclerosis and heart diseases."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "Triglycérides", 50.0, "<150 mg/dL", "≥150 mg/dL",
                    "Measures triglycerides in the blood. High levels are linked to obesity and heart diseases."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "Hémoglobine", 40.0, "12-16 g/dL (women) / 13-18 g/dL (men)", "<12 or >18",
                    "Measures hemoglobin in blood. Indicates anemia or high hemoglobin levels."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "Tension artérielle", 30.0, "120/80 mmHg", ">140/90",
                    "Measures blood pressure. Indicates hypertension or hypotension."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "Urée", 35.0, "2.5-7.5 mmol/L", "<2.5 or >7.5",
                    "Assesses kidney function and protein levels in blood."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "Créatinine", 40.0, "62-106 µmol/L", "<62 or >106",
                    "Evaluates kidney function. High levels indicate potential kidney problems."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "TGO (AST)", 45.0, "10-40 U/L", ">40",
                    "Liver enzyme. Elevated levels indicate liver damage or disease."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "TGP (ALT)", 45.0, "7-56 U/L", ">56",
                    "Liver enzyme. High levels suggest liver injury or disease."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "CRP", 50.0, "<5 mg/L", "≥5",
                    "C-reactive protein. Indicates inflammation in the body."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "Ferritine", 55.0, "30-400 ng/mL", "<30 or >400",
                    "Measures iron stores in the body. Helps detect iron deficiency or overload."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "Vitamine D", 60.0, "30-100 ng/mL", "<30 or >100",
                    "Determines vitamin D levels, important for bone health."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "Protéine C réactive hs", 50.0, "<3 mg/L", "≥3",
                    "High-sensitivity C-reactive protein. Indicates low-grade inflammation, associated with heart disease."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "Glycémie postprandiale", 50.0, "<140 mg/dL", "≥140 mg/dL",
                    "Measures blood sugar after meals. Important for monitoring diabetes."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "Hématocrite", 40.0, "36-46% (women) / 40-54% (men)", "<36 or >54",
                    "Percentage of red blood cells in blood. Indicates anemia or polycythemia."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "Plaquettes", 45.0, "150-400 x10^9/L", "<150 or >400",
                    "Platelet count. Indicates blood clotting ability."
            ));
            analyseService.CreateAnalyse(new AnalyseRequest(
                    "TSH", 55.0, "0.4-4.0 mIU/L", "<0.4 or >4.0",
                    "Thyroid-stimulating hormone. Evaluates thyroid function."
            ));
        }
    }
}
