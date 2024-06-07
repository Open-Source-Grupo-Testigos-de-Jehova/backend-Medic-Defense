package com.medicdefense.backend.resources.domain.model.queries;

public record GetEducationalResourcesByTitleQuery(String title) {
    public GetEducationalResourcesByTitleQuery {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
    }
}