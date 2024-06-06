package com.medicdefense.backend.payment.application.internal.queryservices;

import com.medicdefense.backend.payment.domain.model.aggregates.Payment;
import com.medicdefense.backend.payment.domain.model.queries.GetAllPaymentsQuery;
import com.medicdefense.backend.payment.domain.model.queries.GetPaymentByIdQuery;
import com.medicdefense.backend.payment.infrastructure.persistence.jpa.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentQueryServiceImpl implements PaymentQuerySerivce {

    private final PaymentRepository paymentRepository;

    public PaymentQueryServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> handle(GetPaymentByIdQuery query) {
        return paymentRepository.findById(query.paymentId());
    }

    @Override
    public List<Payment> handle(GetAllPaymentsQuery query) {
        return paymentRepository.findAll();
    }

    public List<Payment> handleGetAllPayments() {
        return paymentRepository.findAll();
    }
}