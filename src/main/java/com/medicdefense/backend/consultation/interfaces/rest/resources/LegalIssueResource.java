package com.medicdefense.backend.consultation.interfaces.rest.resources;

public record LegalIssueResource(Long legalIssueId, String title, String message, Long consultationId, String status) {
}
