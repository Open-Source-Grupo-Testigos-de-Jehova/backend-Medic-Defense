package com.medicdefense.backend.profiles.application.internal.commandservices;

import com.medicdefense.backend.profiles.domain.model.commands.CreateUniversityCommand;
import com.medicdefense.backend.profiles.domain.model.entities.University;
import com.medicdefense.backend.profiles.domain.services.UniversityCommandService;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UniversityCommandServiceImpl implements UniversityCommandService {
    private final UniversityRepository universityRepository;

    public UniversityCommandServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public Optional<University> handle(CreateUniversityCommand command) {
        if(universityRepository.existsByName(command.name()))
            throw new IllegalArgumentException("University with name " + command.name() + " already exists");
        var university = new University(command.name());
        universityRepository.save(university);
        return Optional.of(university);
    }
}
