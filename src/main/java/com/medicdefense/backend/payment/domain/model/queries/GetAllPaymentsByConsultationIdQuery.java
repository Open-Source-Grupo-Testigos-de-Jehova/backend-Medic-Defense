package com.medicdefense.backend.payment.domain.model.queries;

public record GetAllPaymentsByConsultationIdQuery(Long consultationId) {
    public GetAllPaymentsByConsultationIdQuery {
        if (consultationId <= 0) {
            throw new IllegalArgumentException("The identifier must be greater than 0");
        }
    }
}
