package com.medicdefense.backend.payment.infrastructure.persistence.jpa;
import com.medicdefense.backend.payment.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findById(Long id);
    List<Payment> findByConsultationId(Long consultationId);
}
