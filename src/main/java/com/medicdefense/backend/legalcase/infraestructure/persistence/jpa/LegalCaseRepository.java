package com.medicdefense.backend.legalcase.infraestructure.persistence.jpa;

import com.medicdefense.backend.legalcase.domain.model.aggregates.LegalCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LegalCaseRepository extends JpaRepository<LegalCase, Long> {
    List<LegalCase> findAll();
    List<LegalCase> findByDescriptionContaining(String description);
    List<LegalCase> findByStatus(String status);
    Optional<LegalCase> findById(Long id);
    List<LegalCase> findByDescriptionAndStatus(String description, String status);
}