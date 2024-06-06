package com.medicdefense.backend.payment.infrastructure.persistence.jpa;
import com.medicdefense.backend.payment.domain.model.aggregates.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}