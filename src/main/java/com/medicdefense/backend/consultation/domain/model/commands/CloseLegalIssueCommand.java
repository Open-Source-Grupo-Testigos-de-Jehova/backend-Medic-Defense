package com.medicdefense.backend.consultation.domain.model.commands;

public record CloseLegalIssueCommand(Long legalIssueId, Long legalConsultationId) {
}
