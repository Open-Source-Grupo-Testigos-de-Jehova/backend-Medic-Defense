package com.medicdefense.backend.legalcase.interfaces.rest.resources;

public record CreateLegalCaseResource(String caseNumber, String description, String status) {
    public CreateLegalCaseResource {
        if (caseNumber == null || caseNumber.isBlank()) {
            throw new IllegalArgumentException("caseNumber cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status cannot be null or empty");
        }
    }
}