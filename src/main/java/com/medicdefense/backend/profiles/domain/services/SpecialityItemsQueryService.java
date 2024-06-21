package com.medicdefense.backend.profiles.domain.services;

import com.medicdefense.backend.profiles.domain.model.entities.SpecialityItems;
import com.medicdefense.backend.profiles.domain.model.queries.GetAllSpecialitiesQuery;

import java.util.List;
import java.util.Optional;

public interface SpecialityItemsQueryService {
    List<SpecialityItems> handle(GetAllSpecialitiesQuery query);
}
