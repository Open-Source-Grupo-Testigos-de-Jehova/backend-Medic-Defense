package com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories;

import com.medicdefense.backend.profiles.domain.model.aggregate.MedicStudent;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicStudentRepository extends JpaRepository<MedicStudent, Long> {
    Optional<MedicStudent> findByMedicDefenseMedicStudentId(MedicDefenseRecordId medicDefenseRecordId);
    Optional<MedicStudent> findByProfileId(ProfileId profileId);
    boolean existsByMedicDefenseMedicStudentId(MedicDefenseRecordId medicDefenseRecordId);
}