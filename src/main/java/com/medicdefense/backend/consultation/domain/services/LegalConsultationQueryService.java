package com.medicdefense.backend.consultation.domain.services;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.domain.model.queries.GetAllLegalConsultationsQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalConsultationsByIdQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalConsultationsByLawyerIdQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalConsultationsByMedicIdQuery;

import java.util.List;
import java.util.Optional;

public interface LegalConsultationQueryService {
    List<LegalConsultation> handle(GetAllLegalConsultationsQuery query);
    Optional<LegalConsultation> handle(GetLegalConsultationsByIdQuery query);
    List<LegalConsultation> handle(GetLegalConsultationsByLawyerIdQuery query);
    List<LegalConsultation> handle(GetLegalConsultationsByMedicIdQuery query);
}
