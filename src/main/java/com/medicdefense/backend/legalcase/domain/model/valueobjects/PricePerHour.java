package com.medicdefense.backend.legalcase.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PricePerHour(double amount, String currency) {

    public PricePerHour() {
        this(0.0, null);
    }

    public PricePerHour {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than zero");
        if (currency == null || currency.isBlank())
            throw new IllegalArgumentException("Currency cannot be null or empty");
    }

    public String formattedPrice() {
        return String.format("%.2f %s", amount, currency);
    }
}
