package com.medicdefense.backend.consultation.infrastructure.persistence.jpa.repositories;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.domain.model.valueobjects.MedicDefenseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LegalConsultationRepository extends JpaRepository<LegalConsultation, Long> {
    boolean existsByLawyerIdAndMedicId(MedicDefenseId lawyerId, MedicDefenseId medicId);
    Optional<LegalConsultation> findByLawyerIdAndMedicId(MedicDefenseId lawyerId, MedicDefenseId medicId);
    Optional<LegalConsultation> findByLawyerId(MedicDefenseId lawyerId);
    Optional<LegalConsultation> findByMedicId(MedicDefenseId medicId);
    Optional<LegalConsultation> findById(Long legalConsultationId);
    List<LegalConsultation> findAllById(Long legalConsultationId);
    List<LegalConsultation> findAllByLawyerId(MedicDefenseId lawyerId);
    List<LegalConsultation> findAllByMedicId(MedicDefenseId medicId);
}
