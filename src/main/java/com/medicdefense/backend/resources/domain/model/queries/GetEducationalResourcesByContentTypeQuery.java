package com.medicdefense.backend.resources.domain.model.queries;

import com.medicdefense.backend.resources.domain.model.valueobjects.EducationalResourceContent;

public record GetEducationalResourcesByContentTypeQuery(EducationalResourceContent contentType) {
}