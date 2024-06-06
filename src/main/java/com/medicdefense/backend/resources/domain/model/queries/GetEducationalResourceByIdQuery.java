package com.medicdefense.backend.resources.domain.model.queries;

public record GetEducationalResourceByIdQuery(Long id) {
    public GetEducationalResourceByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
    }
}