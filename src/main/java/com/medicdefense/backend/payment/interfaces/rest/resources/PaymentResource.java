package com.medicdefense.backend.payment.interfaces.rest.resources;

import java.util.Date;

public record PaymentResource(int id, Date date, String legalIssue) {
    public PaymentResource(Long id, Date date, String legalIssue, float amount, String method, Long id1) {
        this((int) (long) id, date, legalIssue);
    }
}