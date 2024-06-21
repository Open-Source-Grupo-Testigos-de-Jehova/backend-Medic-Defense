package com.medicdefense.backend.consultation.interfaces.rest.resources;

public record MessageItemResource(Long id, String message, String senderId, Long legalIssueId) {
}
