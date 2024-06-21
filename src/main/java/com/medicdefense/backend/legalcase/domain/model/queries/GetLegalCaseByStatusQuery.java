package com.medicdefense.backend.legalcase.domain.model.queries;

import com.medicdefense.backend.legalcase.domain.model.valueobjects.LegalCaseStatus;

public record GetLegalCaseByStatusQuery(LegalCaseStatus status) {
}