package com.medicdefense.backend.payment.domain.model.valueobjets;

import jakarta.persistence.Embeddable;

/**
 * PaymentMethod is a value object that encapsulates the payment method.
 */
@Embeddable
public record PaymentMethod(String method) {

    public PaymentMethod() {
        this(null);
    }

    public PaymentMethod {
        if (method == null || method.isBlank()) {
            throw new IllegalArgumentException("The payment method cannot be null or empty");
        }
    }
}
