package com.medicdefense.backend.legalcase.domain.model.commands;

public record CreateLegalCaseCommand(String caseNumber, String description, String status) {
    public CreateLegalCaseCommand {
        if (caseNumber == null || caseNumber.isBlank()) {
            throw new IllegalArgumentException("caseNumber cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status cannot be null or empty");
        }
    }
}