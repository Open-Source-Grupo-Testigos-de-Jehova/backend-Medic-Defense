package com.medicdefense.backend.resources.application.internal.queryservices;

import com.medicdefense.backend.resources.domain.model.aggregates.EducationalResource;
import com.medicdefense.backend.resources.domain.model.queries.*;
import com.medicdefense.backend.resources.domain.services.EducationalResourceQueryService;
import com.medicdefense.backend.resources.infraestructure.persistence.jpa.EducationalResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationalResourceQueryServiceImpl implements EducationalResourceQueryService {
    private final EducationalResourceRepository educationalResourceRepository;

    public EducationalResourceQueryServiceImpl(EducationalResourceRepository educationalResourceRepository) {
        this.educationalResourceRepository = educationalResourceRepository;
    }

    @Override
    public List<EducationalResource> handle(GetAllEducationalResourcesQuery query) {
        return educationalResourceRepository.findAll(); // This line needs to be modified to handle the filter parameter appropriately.
    }

    @Override
    public Optional<EducationalResource> handle(GetEducationalResourceByIdQuery query) {
        return educationalResourceRepository.findById(query.id());
    }

    @Override
    public List<EducationalResource> handle(GetEducationalResourcesByTitleQuery query) {
        return educationalResourceRepository.findByTitleContaining(query.title());
    }

    @Override
    public List<EducationalResource> handle(GetEducationalResourcesByContentTypeQuery query) {
        return educationalResourceRepository.findByContentType(query.contentType());
    }

    @Override
    public List<EducationalResource> handle(GetEducationalResourcesByAuthorQuery query) {
        return educationalResourceRepository.findByAuthor(query.author());
    }
}