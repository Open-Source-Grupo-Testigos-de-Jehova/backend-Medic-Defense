package com.medicdefense.backend.consultation.domain.model.commands;

public record RespondLegalIssueCommand(Long legalConsultationId, Long legalIssueId, String response) {
}
