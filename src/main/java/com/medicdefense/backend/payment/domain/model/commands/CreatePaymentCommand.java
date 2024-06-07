package com.medicdefense.backend.payment.domain.model.commands;

public record CreatePaymentCommand(Long consultationId, float amount, String method) {
    public CreatePaymentCommand {
        if (consultationId == null) {
            throw new IllegalArgumentException("The consultation id cannot be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("The amount must be greater than 0");
        }
        if (method == null || method.isBlank()) {
            throw new IllegalArgumentException("The payment method cannot be null or empty");
        }
    }
}
