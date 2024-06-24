package com.medicdefense.backend.legalcase.infrastructure.persistence.jpa;

import com.medicdefense.backend.legalcase.domain.model.aggregates.LegalCase;
import com.medicdefense.backend.legalcase.domain.model.valueobjects.LegalCaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This interface is responsible for managing the LegalCase entities in the database.
 */

@Repository
public interface LegalCaseRepository extends JpaRepository<LegalCase, Long> {
    List<LegalCase> findByDescriptionContaining(String description);
    List<LegalCase> findByStatus(LegalCaseStatus status);
}