package com.medicdefense.backend.consultation.infrastructure.persistence.jpa.repositories;

import com.medicdefense.backend.consultation.domain.model.entities.LegalIssueItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LegalIssueRepository extends JpaRepository<LegalIssueItem, Long> {
    List<LegalIssueItem> findAllByLegalConsultation_Id(Long legalConsultationId);
}
