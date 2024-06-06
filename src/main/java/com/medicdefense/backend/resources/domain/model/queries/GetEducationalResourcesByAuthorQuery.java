package com.medicdefense.backend.resources.domain.model.queries;

public record GetEducationalResourcesByAuthorQuery(String author) {
    public GetEducationalResourcesByAuthorQuery {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
    }
}