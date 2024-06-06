package com.medicdefense.backend.payment.infrastructure.persistence.jpa;
import com.medicdefense.backend.payment.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAll();

}