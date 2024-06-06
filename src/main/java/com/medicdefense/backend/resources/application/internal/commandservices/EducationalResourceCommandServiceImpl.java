package com.medicdefense.backend.resources.application.internal.commandservices;

import com.medicdefense.backend.resources.domain.model.aggregates.EducationalResource;
import com.medicdefense.backend.resources.domain.model.commands.CreateEducationalResourceCommand;
import com.medicdefense.backend.resources.domain.services.EducationalResourceCommandService;
import com.medicdefense.backend.resources.infraestructure.persistence.jpa.EducationalResourceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EducationalResourceCommandServiceImpl implements EducationalResourceCommandService {
    private final EducationalResourceRepository educationalResourceRepository;

    public EducationalResourceCommandServiceImpl(EducationalResourceRepository educationalResourceRepository) {
        this.educationalResourceRepository = educationalResourceRepository;
    }

    /**
     * Handles the CreateEducationalResourceCommand command.
     * @param command - The CreateEducationalResourceCommand command
     * @return an Optional of EducationalResource
     */
    @Override
    public Optional<EducationalResource> handle(CreateEducationalResourceCommand command) {
        var educationalResource = new EducationalResource(command);
        var createdEducationalResource = educationalResourceRepository.save(educationalResource);
        return Optional.of(createdEducationalResource);
    }
}