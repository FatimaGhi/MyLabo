package com.example.Mylab.Repository;

import com.example.Mylab.Model.Analyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnalyseRepo extends JpaRepository<Analyse, UUID> {

    Optional<Analyse> findByNom(String nom);
}
