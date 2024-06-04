package com.medicdefense.backend.consultation.infrastructure.persistence.jpa.repositories;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.domain.model.valueobjects.MedicDefenseLegalConsultationRecordId;
import com.medicdefense.backend.consultation.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<LegalConsultation, Long> {
    Optional<LegalConsultation> findByMedicDefenseLegalConsultationRecordId(MedicDefenseLegalConsultationRecordId consultationRecordId);
    Optional<LegalConsultation> findByLawyerIdAndMedicId(ProfileId lawyerId, ProfileId medicId);
    Optional<LegalConsultation> findByLawyerId(ProfileId lawyerId);
    Optional<LegalConsultation> findByMedicId(ProfileId medicId);
    List<LegalConsultation> findAllByMedicDefenseLegalConsultationRecordId(MedicDefenseLegalConsultationRecordId consultationRecordId);
    List<LegalConsultation> findAllByLawyerId(ProfileId lawyerId);
    List<LegalConsultation> findAllByMedicId(ProfileId medicId);
}
