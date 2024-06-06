package com.medicdefense.backend.payment.interfaces.rest.resources;

import java.util.Date;

public record CreatePaymentResource( Long consultationId,String method,float amount) {
    public CreatePaymentResource {
        if (amount <= 0) {
            throw new IllegalArgumentException("The amount must be greater than 0");
        }
        if (method == null || method.isBlank()) {
            throw new IllegalArgumentException("The method cannot be null or empty");
        }
        if (consultationId == null) {
            throw new IllegalArgumentException("The consultation id cannot be null");
        }
    }
}