package com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories;

import com.medicdefense.backend.profiles.domain.model.aggregate.Lawyer;
import com.medicdefense.backend.profiles.domain.model.aggregate.Medic;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicRepository extends JpaRepository<Medic, Long> {
    Optional<Medic> findByMedicDefenseMedicId(MedicDefenseRecordId medicDefenseRecordId);
    Optional<Medic> findByProfileId(ProfileId profileId);
    boolean existsByMedicDefenseMedicId(MedicDefenseRecordId medicDefenseRecordId);
}