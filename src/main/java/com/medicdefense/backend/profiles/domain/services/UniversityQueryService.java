package com.medicdefense.backend.profiles.domain.services;

import com.medicdefense.backend.profiles.domain.model.entities.University;
import com.medicdefense.backend.profiles.domain.model.queries.GetAllUniversitiesQuery;

import java.util.List;
import java.util.Optional;

public interface UniversityQueryService {
    List<University> handle(GetAllUniversitiesQuery query);
}
