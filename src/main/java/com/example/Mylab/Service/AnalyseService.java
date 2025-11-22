package com.example.Mylab.Service;


import com.example.Mylab.DTO.AnalyseRequest;
import com.example.Mylab.Model.Analyse;
import com.example.Mylab.Repository.AnalyseRepo;
import org.springframework.stereotype.Service;

@Service
public class AnalyseService {
    private AnalyseRepo analyseRepo;

    public AnalyseService(AnalyseRepo analyseRepo){
        this.analyseRepo = analyseRepo;
    }


    public Analyse CreateAnalyse(AnalyseRequest analyseRequest){

        Analyse analyse = Analyse.builder()
                .nom(analyseRequest.getNom())
                .prix(analyseRequest.getPrix())
                .Abnormal(analyseRequest.getAbnormal())
                .valeurNormale(analyseRequest.getValeurNormale())
                .description(analyseRequest.getDescription())
                .build();
        return  analyseRepo.save(analyse);
    }
}
