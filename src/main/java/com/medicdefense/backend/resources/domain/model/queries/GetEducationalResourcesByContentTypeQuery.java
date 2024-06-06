package com.medicdefense.backend.resources.domain.model.queries;

public record GetEducationalResourcesByContentTypeQuery(String contentType) {
    public GetEducationalResourcesByContentTypeQuery {
        if (contentType == null || contentType.isBlank()) {
            throw new IllegalArgumentException("Content Type cannot be null or empty");
        }
    }
}