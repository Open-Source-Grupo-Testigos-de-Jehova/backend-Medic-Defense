package com.medicdefense.backend.payment.interfaces.rest.transform;

import com.medicdefense.backend.payment.domain.model.aggregates.Payment;
import com.medicdefense.backend.payment.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {

    public static PaymentResource toResourceFromEntity(Payment entity) {
        return new PaymentResource(
                entity.getId(),
                entity.getDate(),
                entity.getLegalIssue(),
                entity.getAmount(),
                entity.getMethod(),
                entity.getConsultation().getId()
        );
    }
}
