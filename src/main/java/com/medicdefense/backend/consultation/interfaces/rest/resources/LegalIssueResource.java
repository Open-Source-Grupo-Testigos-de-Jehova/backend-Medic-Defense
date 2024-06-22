package com.medicdefense.backend.consultation.interfaces.rest.resources;

public record LegalIssueResource(Long legalIssueId, String consultation, String response, Long consultationId, String status) {
}
