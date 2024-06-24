package com.medicdefense.backend.legalcase.interfaces.rest.resources;

/**
 * This class is responsible for converting a LegalCase into a LegalCaseResource.
 */

public record CreateLegalCaseResource(String description, String lawyerId, String clientId) {
}