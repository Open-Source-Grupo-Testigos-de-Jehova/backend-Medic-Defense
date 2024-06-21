package com.medicdefense.backend.legalcase.domain.model.commands;

public record CreateLegalCaseCommand(String description, String lawyerId, String clientId) {
    public CreateLegalCaseCommand {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
        if (lawyerId == null || lawyerId.isBlank()) {
            throw new IllegalArgumentException("lawyerId cannot be null or empty");
        }
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("clientId cannot be null or empty");
        }
    }
}