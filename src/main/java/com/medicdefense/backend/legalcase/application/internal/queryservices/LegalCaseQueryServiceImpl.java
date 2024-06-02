package com.medicdefense.backend.legalcase.application.internal.queryservices;

import com.medicdefense.backend.legalcase.domain.model.aggregates.LegalCase;
import com.medicdefense.backend.legalcase.domain.model.queries.GetLegalCaseByDescriptionQuery;
import com.medicdefense.backend.legalcase.domain.model.queries.GetLegalCaseByIdQuery;
import com.medicdefense.backend.legalcase.domain.model.queries.GetLegalCaseByStatusQuery;
import com.medicdefense.backend.legalcase.domain.services.LegalCaseQueryService;
import com.medicdefense.backend.legalcase.infrastructure.persistence.jpa.LegalCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LegalCaseQueryServiceImpl implements LegalCaseQueryService {
    private final LegalCaseRepository legalCaseRepository;

    public LegalCaseQueryServiceImpl(LegalCaseRepository legalCaseRepository) {
        this.legalCaseRepository = legalCaseRepository;
    }

    @Override
    public Optional<LegalCase> handle(GetLegalCaseByIdQuery query) {
        return legalCaseRepository.findById(query.id());
    }

    @Override
    public List<LegalCase> handle(GetLegalCaseByDescriptionQuery query) {
        return legalCaseRepository.findByDescriptionContaining(query.description());
    }

    @Override
    public List<LegalCase> handle(GetLegalCaseByStatusQuery query) {
        return legalCaseRepository.findByStatus(query.status());
    }
}