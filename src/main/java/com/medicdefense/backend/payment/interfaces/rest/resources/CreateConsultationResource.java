package com.medicdefense.backend.payment.interfaces.rest.resources;

public record CreateConsultationResource(String legalIssue) {
    public CreateConsultationResource {
        if (legalIssue == null || legalIssue.isBlank()) {
            throw new IllegalArgumentException("The legal issue cannot be null or empty");
        }
    }
}
