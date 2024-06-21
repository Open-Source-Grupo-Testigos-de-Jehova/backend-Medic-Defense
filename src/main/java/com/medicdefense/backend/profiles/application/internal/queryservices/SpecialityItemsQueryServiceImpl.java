package com.medicdefense.backend.profiles.application.internal.queryservices;

import com.medicdefense.backend.profiles.domain.model.entities.SpecialityItems;
import com.medicdefense.backend.profiles.domain.model.queries.GetAllSpecialitiesQuery;
import com.medicdefense.backend.profiles.domain.services.SpecialityItemsQueryService;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.SpecialityItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityItemsQueryServiceImpl implements SpecialityItemsQueryService {
    private final SpecialityItemsRepository specialityItemsRepository;

    public SpecialityItemsQueryServiceImpl(SpecialityItemsRepository specialityItemsRepository) {
        this.specialityItemsRepository = specialityItemsRepository;
    }

    @Override
    public List<SpecialityItems> handle(GetAllSpecialitiesQuery query) {
        return specialityItemsRepository.findAll();
    }
}
