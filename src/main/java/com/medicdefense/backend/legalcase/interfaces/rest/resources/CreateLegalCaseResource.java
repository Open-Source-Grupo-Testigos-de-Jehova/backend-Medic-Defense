package com.medicdefense.backend.legalcase.interfaces.rest.resources;

public record CreateLegalCaseResource(String description, String lawyerId, String clientId) {
}