package com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories;

import com.medicdefense.backend.profiles.domain.model.entities.SpecialityItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialityItemsRepository extends JpaRepository<SpecialityItems, Long> {
    boolean existsByName(String name);
    Optional<SpecialityItems> findByName(String name);
}
