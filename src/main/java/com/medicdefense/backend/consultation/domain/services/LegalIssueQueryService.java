package com.medicdefense.backend.consultation.domain.services;

import com.medicdefense.backend.consultation.domain.model.entities.LegalIssueItem;
import com.medicdefense.backend.consultation.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface LegalIssueQueryService {
    Optional<LegalIssueItem> handle(GetLegalIssueByIdQuery query);
    List<LegalIssueItem> handle(GetLegalIssuesByConsultationIdQuery query);
    List<LegalIssueItem> handle(GetAllLegalIssuesQuery query);
}