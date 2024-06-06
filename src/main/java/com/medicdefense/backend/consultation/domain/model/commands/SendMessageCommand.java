package com.medicdefense.backend.consultation.domain.model.commands;

public record SendMessageCommand(Long LegalIssueId, String message) {
}
