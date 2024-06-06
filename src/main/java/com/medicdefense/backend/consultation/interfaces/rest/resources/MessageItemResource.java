package com.medicdefense.backend.consultation.interfaces.rest.resources;

public record MessageItemResource(Long id, String message, Long senderId, Long legalIssueId) {
}
