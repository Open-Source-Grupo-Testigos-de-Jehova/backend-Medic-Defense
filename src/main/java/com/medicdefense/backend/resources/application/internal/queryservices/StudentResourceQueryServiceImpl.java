package com.medicdefense.backend.resources.application.internal.queryservices;

import com.medicdefense.backend.resources.domain.model.entities.StudentResource;
import com.medicdefense.backend.resources.domain.model.queries.GetAllStudentResourcesQuery;
import com.medicdefense.backend.resources.domain.model.queries.GetStudentResourcesByStudentId;
import com.medicdefense.backend.resources.domain.services.StudentResourceQueryService;
import com.medicdefense.backend.resources.infrastructure.persistence.jpa.StudentResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentResourceQueryServiceImpl implements StudentResourceQueryService {
    private final StudentResourceRepository studentResourceRepository;

    public StudentResourceQueryServiceImpl(StudentResourceRepository studentResourceRepository) {
        this.studentResourceRepository = studentResourceRepository;
    }

    @Override
    public List<StudentResource> handle(GetAllStudentResourcesQuery query) {
        return studentResourceRepository.findAll();
    }

    @Override
    public List<StudentResource> handle(GetStudentResourcesByStudentId query) {
        return studentResourceRepository.findByStudentRecordId(query.studentId());
    }
}
