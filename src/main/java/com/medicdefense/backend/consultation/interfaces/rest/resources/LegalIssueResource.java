package com.medicdefense.backend.consultation.interfaces.rest.resources;

public record LegalIssueResource(Long LegalIssueId, String Title, String Message, Long ConsultationId, String Status) {
}
