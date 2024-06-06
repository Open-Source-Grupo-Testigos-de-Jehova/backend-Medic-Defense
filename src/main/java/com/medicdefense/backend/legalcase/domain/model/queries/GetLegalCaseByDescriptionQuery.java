package com.medicdefense.backend.legalcase.domain.model.queries;

public record GetLegalCaseByDescriptionQuery(String description) {
    public GetLegalCaseByDescriptionQuery {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }
}