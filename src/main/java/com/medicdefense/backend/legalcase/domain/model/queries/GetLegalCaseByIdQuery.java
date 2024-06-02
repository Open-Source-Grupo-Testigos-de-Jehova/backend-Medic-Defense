package com.medicdefense.backend.legalcase.domain.model.queries;

public record GetLegalCaseByIdQuery(Long id) {
    public GetLegalCaseByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
    }
}