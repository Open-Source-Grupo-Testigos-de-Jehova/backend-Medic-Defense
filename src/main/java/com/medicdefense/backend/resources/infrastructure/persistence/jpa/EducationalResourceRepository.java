package com.medicdefense.backend.resources.infrastructure.persistence.jpa;


import com.medicdefense.backend.resources.domain.model.aggregates.EducationalResource;
import com.medicdefense.backend.resources.domain.model.valueobjects.EducationalResourceContent;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationalResourceRepository extends JpaRepository<EducationalResource, Long> {
    List<EducationalResource> findByTitleContaining(String title);
    List<EducationalResource> findByAuthor(String author);
    List<EducationalResource> findByContentType(EducationalResourceContent contentType);
}