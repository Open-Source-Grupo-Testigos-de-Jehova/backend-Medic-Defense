package com.medicdefense.backend.payment.interfaces.rest.resources;

import java.util.Date;

public record CreatePaymentResource(Date date, String legalIssue, float amount, String method, int consultationId) {
    public CreatePaymentResource {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (legalIssue == null) {
            throw new IllegalArgumentException("Legal issue cannot be null");
        }
        if (method == null || method.isBlank()) {
            throw new IllegalArgumentException("Payment method cannot be null or empty");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than 0");
        }
        if (consultationId <= 0) {
            throw new IllegalArgumentException("Consultation ID must be greater than 0");
        }
    }
}