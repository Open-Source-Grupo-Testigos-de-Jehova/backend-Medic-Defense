package com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories;

import com.medicdefense.backend.profiles.domain.model.aggregate.Lawyer;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
    Optional<Lawyer> findByMedicDefenseRecordId(MedicDefenseRecordId medicDefenseRecordId);
    Optional<Lawyer> findByProfileId(ProfileId profileId);
    boolean existsByMedicDefenseRecordId(MedicDefenseRecordId medicDefenseRecordId);
    Optional<Lawyer> findByUserId(UserId userId);
}
