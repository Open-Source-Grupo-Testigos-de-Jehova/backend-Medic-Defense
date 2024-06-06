package com.medicdefense.backend.payment.domain.services;

import com.medicdefense.backend.payment.domain.model.aggregates.Consultation;
import com.medicdefense.backend.payment.domain.model.queries.GetConsultationByIdQuery;

import java.util.Optional;

public interface ConsultationQueryService {
    Optional<Consultation> handle(GetConsultationByIdQuery query);
}