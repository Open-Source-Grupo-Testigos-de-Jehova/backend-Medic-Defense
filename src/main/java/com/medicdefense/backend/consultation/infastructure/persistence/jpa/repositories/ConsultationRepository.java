package com.medicdefense.backend.consultation.infastructure.persistence.jpa.repositories;

import com.medicdefense.backend.consultation.domain.model.aggregate.Consultation;
import com.medicdefense.backend.consultation.domain.model.valueobjects.MedicDefenseConsultationRecordId;
import com.medicdefense.backend.consultation.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Optional<Consultation> findByMedicDefenseConsultationRecordId(MedicDefenseConsultationRecordId consultationRecordId);
    Optional<Consultation> findByLawyerIdAndMedicId(ProfileId lawyerId, ProfileId medicId);
    Optional<Consultation> findByLawyerId(ProfileId lawyerId);
    Optional<Consultation> findByMedicId(ProfileId medicId);
    List<Consultation> findAllByMedicDefenseConsultationRecordId(MedicDefenseConsultationRecordId consultationRecordId);
    List<Consultation> findAllByLawyerId(ProfileId lawyerId);
    List<Consultation> findAllByMedicId(ProfileId medicId);
}
