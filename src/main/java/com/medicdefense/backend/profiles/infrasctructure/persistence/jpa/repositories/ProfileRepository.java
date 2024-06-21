package com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories;

import com.medicdefense.backend.profiles.domain.model.aggregate.Profile;
import com.medicdefense.backend.profiles.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByEmail(EmailAddress emailAddress);
    boolean existsByEmail(EmailAddress emailAddress);
}
