package com.medicdefense.backend.resources.infraestructure.persistence.jpa;


import com.medicdefense.backend.resources.domain.model.aggregates.EducationalResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationalResourceRepository extends JpaRepository<EducationalResource, Long> {
    List<EducationalResource> findAll();
    List<EducationalResource> findByTitleContaining(String title);
    List<EducationalResource> findByContentType(String contentType);
    List<EducationalResource> findByAuthor(String author);
    Optional<EducationalResource> findById(Long id);
    List<EducationalResource> findByAuthorAndContentType(String author, String contentType);
}