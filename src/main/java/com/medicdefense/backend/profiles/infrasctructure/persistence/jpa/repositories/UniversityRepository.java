package com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories;

import com.medicdefense.backend.profiles.domain.model.entities.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    boolean existsByName(String name);
    Optional<University> findByName(String name);
}
