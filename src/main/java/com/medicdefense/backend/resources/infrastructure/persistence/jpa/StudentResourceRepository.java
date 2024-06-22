package com.medicdefense.backend.resources.infrastructure.persistence.jpa;

import com.medicdefense.backend.resources.domain.model.entities.StudentResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentResourceRepository extends JpaRepository<StudentResource, Long> {
    List<StudentResource> findByStudentRecordId(String studentRecordId);
}
