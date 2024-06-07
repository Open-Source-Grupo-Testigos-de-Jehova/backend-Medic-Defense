package com.medicdefense.backend.payment.application.internal.queryservices;

import com.medicdefense.backend.payment.domain.model.aggregates.Consultation;
import com.medicdefense.backend.payment.domain.model.queries.GetConsultationByIdQuery;
import com.medicdefense.backend.payment.infrastructure.persistence.jpa.ConsultationRepository;
import com.medicdefense.backend.payment.domain.services.ConsultationQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultationQueryServiceImpl implements ConsultationQueryService {

    private final ConsultationRepository consultationRepository;

    public ConsultationQueryServiceImpl(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Optional<Consultation> handle(GetConsultationByIdQuery query) {
        return consultationRepository.findById((long) query.id());
    }
}
