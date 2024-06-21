package com.medicdefense.backend.profiles.application.internal.queryservices;

import com.medicdefense.backend.profiles.domain.model.entities.University;
import com.medicdefense.backend.profiles.domain.model.queries.GetAllUniversitiesQuery;
import com.medicdefense.backend.profiles.domain.services.UniversityQueryService;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityQueryServiceImpl implements UniversityQueryService {
    private final UniversityRepository universityRepository;

    public UniversityQueryServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public List<University> handle(GetAllUniversitiesQuery query) {
        return universityRepository.findAll();
    }
}
