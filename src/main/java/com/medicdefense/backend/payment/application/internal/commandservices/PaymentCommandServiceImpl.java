package com.medicdefense.backend.payment.application.internal.commandservices;

import com.medicdefense.backend.payment.domain.model.aggregates.Consultation;
import com.medicdefense.backend.payment.domain.model.aggregates.Payment;
import com.medicdefense.backend.payment.domain.services.PaymentCommandService;
import com.medicdefense.backend.payment.infrastructure.persistence.jpa.ConsultationRepository;
import com.medicdefense.backend.payment.infrastructure.persistence.jpa.PaymentRepository;
import com.medicdefense.backend.payment.domain.model.commands.CreatePaymentCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository paymentRepository;
    private final ConsultationRepository consultationRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository, ConsultationRepository consultationRepository) {
        this.paymentRepository = paymentRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {
        Consultation consultation = consultationRepository.findById(command.consultationId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid consultation id"));
        Payment payment = new Payment(command, consultation);
        Payment createdPayment = paymentRepository.save(payment);
        return Optional.of(createdPayment);
    }
}
