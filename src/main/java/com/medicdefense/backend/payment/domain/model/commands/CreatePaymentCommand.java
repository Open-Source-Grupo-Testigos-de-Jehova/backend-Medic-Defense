package com.medicdefense.backend.payment.domain.model.commands;

import java.util.Date;

public class CreatePaymentCommand {
    private final float amount;
    private final String method;
    private final int consultationId;

    public CreatePaymentCommand(float amount, String method, int consultationId) {
        if (method == null || method.isBlank()) {
            throw new IllegalArgumentException("Payment method cannot be null or empty");
        }
        this.amount = amount;
        this.method = method;
        this.consultationId = consultationId;
    }

    public CreatePaymentCommand(Date date, String method, float amount, String method1, int i, float amount1, String method2, int consultationId) {

        this.amount = amount1;
        this.method = method2;
        this.consultationId = consultationId;
    }

    public CreatePaymentCommand(Date date, String s, float amount, String method, int i) {
        this.amount = amount;
        this.method = method;
        this.consultationId = i;
    }

    public float amount() {
        return amount;
    }

    public String method() {
        return method;
    }

    public int consultationId() {
        return consultationId;
    }
}
