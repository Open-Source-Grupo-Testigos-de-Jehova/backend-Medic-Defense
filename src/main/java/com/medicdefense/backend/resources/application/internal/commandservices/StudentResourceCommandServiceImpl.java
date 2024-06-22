package com.medicdefense.backend.resources.application.internal.commandservices;

import com.medicdefense.backend.resources.domain.model.commands.CreateStudentResourceCommand;
import com.medicdefense.backend.resources.domain.model.entities.StudentResource;
import com.medicdefense.backend.resources.domain.services.StudentResourceCommandService;
import com.medicdefense.backend.resources.infrastructure.persistence.jpa.EducationalResourceRepository;
import com.medicdefense.backend.resources.infrastructure.persistence.jpa.StudentResourceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentResourceCommandServiceImpl implements StudentResourceCommandService {
    private final StudentResourceRepository studentResourceRepository;
    private final EducationalResourceRepository educationalResourceRepository;

    public StudentResourceCommandServiceImpl(StudentResourceRepository studentResourceRepository, EducationalResourceRepository educationalResourceRepository) {
        this.studentResourceRepository = studentResourceRepository;
        this.educationalResourceRepository = educationalResourceRepository;
    }

    @Override
    public Optional<StudentResource> handle(CreateStudentResourceCommand command) {
        var studentResource = new StudentResource(command);
        var createdStudentResource = studentResourceRepository.save(studentResource);
        return Optional.of(createdStudentResource);
    }
}
