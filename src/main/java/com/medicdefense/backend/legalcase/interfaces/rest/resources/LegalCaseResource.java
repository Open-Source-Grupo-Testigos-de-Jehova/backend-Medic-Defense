package com.medicdefense.backend.legalcase.interfaces.rest.resources;

/**
 * This class is responsible for converting a LegalCase into a LegalCaseResource.
 */

public record LegalCaseResource(Long id, String description, String status, String lawyerId, String clientId) {
}