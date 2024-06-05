package com.medicdefense.backend.payment.domain.services;

import com.medicdefense.backend.payment.domain.model.aggregates.Payment;
import com.medicdefense.backend.payment.domain.model.queries.GetAllPaymentsQuery;
import com.medicdefense.backend.payment.domain.model.queries.GetPaymentByIdQuery;
import java.util.List;
import java.util.Optional;

public interface PaymentQueryService {
    Optional<Payment> handle(GetPaymentByIdQuery query);
    List<Payment> handle(GetAllPaymentsQuery query);
}
