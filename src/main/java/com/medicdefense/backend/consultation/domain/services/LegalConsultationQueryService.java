package com.medicdefense.backend.consultation.domain.services;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.domain.model.entities.LegalIssueItem;
import com.medicdefense.backend.consultation.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface LegalConsultationQueryService {
    List<LegalConsultation> handle(GetAllLegalConsultationsQuery query);
    Optional<LegalConsultation> handle(GetLegalConsultationsByIdQuery query);
    List<LegalConsultation> handle(GetLegalConsultationsByLawyerIdQuery query);
    List<LegalConsultation> handle(GetLegalConsultationsByMedicIdQuery query);
    Optional<LegalIssueItem> handle(GetLegalIssueByIdAndConsultationIdQuery query);
    Optional<LegalIssueItem> handle(GetLegalIssueByNameAndConsultationIdQuery query);
}
