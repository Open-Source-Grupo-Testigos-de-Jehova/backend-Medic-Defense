package com.medicdefense.backend.consultation.domain.model.commands;

public record AskLegalIssueCommand (String title, Long legalConsultationId, String FirstMessage) {
}
