package com.medicdefense.backend.profiles.domain.services;

import com.medicdefense.backend.profiles.domain.model.commands.CreateUniversityCommand;
import com.medicdefense.backend.profiles.domain.model.entities.University;

import java.util.Optional;

public interface UniversityCommandService {
    Optional<University> handle(CreateUniversityCommand command);
}
