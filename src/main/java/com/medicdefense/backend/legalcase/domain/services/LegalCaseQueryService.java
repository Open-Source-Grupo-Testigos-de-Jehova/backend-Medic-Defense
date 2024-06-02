package com.medicdefense.backend.legalcase.domain.services;

import com.medicdefense.backend.legalcase.domain.model.aggregates.LegalCase;
import com.medicdefense.backend.legalcase.domain.model.queries.GetLegalCaseByDescriptionQuery;
import com.medicdefense.backend.legalcase.domain.model.queries.GetLegalCaseByIdQuery;
import com.medicdefense.backend.legalcase.domain.model.queries.GetLegalCaseByStatusQuery;

import java.util.List;
import java.util.Optional;

public interface LegalCaseQueryService {
    Optional<LegalCase> handle(GetLegalCaseByIdQuery query);
    List<LegalCase> handle(GetLegalCaseByDescriptionQuery query);
    List<LegalCase> handle(GetLegalCaseByStatusQuery query);
}