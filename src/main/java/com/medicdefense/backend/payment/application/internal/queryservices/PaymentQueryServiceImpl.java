package com.medicdefense.backend.payment.application.internal.queryservices;

import com.medicdefense.backend.payment.domain.model.aggregates.Payment;
import com.medicdefense.backend.payment.domain.model.queries.GetAllPaymentsByConsultationIdQuery;
import com.medicdefense.backend.payment.domain.model.queries.GetPaymentByIdQuery;
import com.medicdefense.backend.payment.domain.services.PaymentQueryService;
import com.medicdefense.backend.payment.infrastructure.persistence.jpa.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;

    public PaymentQueryServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment>handle(GetPaymentByIdQuery  query) {
        return paymentRepository.findById(query.id());
    }

    @Override
    public List<Payment> handle(GetAllPaymentsByConsultationIdQuery query){
        return paymentRepository.findByConsultationId(query.consultationId());
    }
}