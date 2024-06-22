package com.medicdefense.backend.resources.domain.services;

import com.medicdefense.backend.resources.domain.model.commands.CreateStudentResourceCommand;
import com.medicdefense.backend.resources.domain.model.entities.StudentResource;

import java.util.Optional;

public interface StudentResourceCommandService {
    Optional<StudentResource> handle(CreateStudentResourceCommand command);
}
