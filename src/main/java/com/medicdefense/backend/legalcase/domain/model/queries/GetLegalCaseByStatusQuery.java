package com.medicdefense.backend.legalcase.domain.model.queries;

public record GetLegalCaseByStatusQuery(String status) {
    public GetLegalCaseByStatusQuery {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}