package com.medicdefense.backend.legalcase.interfaces.rest.resources;

public record LegalCaseResource(Long id, String description, String status, String lawyerId, String clientId) {
}