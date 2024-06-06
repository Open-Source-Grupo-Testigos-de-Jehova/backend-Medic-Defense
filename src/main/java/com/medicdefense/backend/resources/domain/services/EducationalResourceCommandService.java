package com.medicdefense.backend.resources.domain.services;

import com.medicdefense.backend.resources.domain.model.aggregates.EducationalResource;
import com.medicdefense.backend.resources.domain.model.commands.CreateEducationalResourceCommand;

import java.util.Optional;

public interface EducationalResourceCommandService {
    Optional<EducationalResource> handle(CreateEducationalResourceCommand command);
}