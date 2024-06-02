package com.medicdefense.backend.resources.domain.services;

import com.medicdefense.backend.resources.domain.model.aggregates.EducationalResource;
import com.medicdefense.backend.resources.domain.model.queries.GetEducationalResourceByIdQuery;
import com.medicdefense.backend.resources.domain.model.queries.GetEducationalResourcesByAuthorQuery;
import com.medicdefense.backend.resources.domain.model.queries.GetEducationalResourcesByContentTypeQuery;
import com.medicdefense.backend.resources.domain.model.queries.GetEducationalResourcesByTitleQuery;

import java.util.List;
import java.util.Optional;

public interface EducationalResourceQueryService {
    List<EducationalResource> handle(GetEducationalResourcesByTitleQuery query);
    List<EducationalResource> handle(GetEducationalResourcesByContentTypeQuery query);
    List<EducationalResource> handle(GetEducationalResourcesByAuthorQuery query);
    Optional<EducationalResource> handle(GetEducationalResourceByIdQuery query);
}