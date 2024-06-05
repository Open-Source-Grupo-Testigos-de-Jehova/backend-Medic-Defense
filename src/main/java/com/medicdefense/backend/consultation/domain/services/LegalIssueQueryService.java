package com.medicdefense.backend.consultation.domain.services;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalIssue;
import com.medicdefense.backend.consultation.domain.model.queries.GetAllLegalIssuesQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalIssueByIdQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalIssuesByConsultationIdQuery;

import java.util.List;
import java.util.Optional;

public interface LegalIssueQueryService {
    Optional<LegalIssue> handle(GetLegalIssueByIdQuery query);
    List<LegalIssue> handle(GetLegalIssuesByConsultationIdQuery query);
    List<LegalIssue> handle(GetAllLegalIssuesQuery query);
}