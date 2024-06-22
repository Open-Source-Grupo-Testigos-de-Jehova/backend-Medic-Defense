package com.medicdefense.backend.consultation.application.internal.queryservices;

import com.medicdefense.backend.consultation.domain.model.entities.LegalIssueItem;
import com.medicdefense.backend.consultation.domain.model.queries.*;
import com.medicdefense.backend.consultation.domain.services.LegalIssueQueryService;
import com.medicdefense.backend.consultation.infrastructure.persistence.jpa.repositories.LegalIssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LegalIssueQueryServiceImpl implements LegalIssueQueryService {

    private final LegalIssueRepository legalIssueRepository;

    public LegalIssueQueryServiceImpl(LegalIssueRepository legalIssueRepository) {
        this.legalIssueRepository = legalIssueRepository;
    }

    @Override
    public Optional<LegalIssueItem> handle(GetLegalIssueByIdQuery query) {
        return legalIssueRepository.findById(query.legalIssueId());
    }

    @Override
    public List<LegalIssueItem> handle(GetLegalIssuesByConsultationIdQuery query) {
        return legalIssueRepository.findAllByLegalConsultation_Id(query.consultationId());
    }

    @Override
    public List<LegalIssueItem> handle(GetAllLegalIssuesQuery query) {
        return legalIssueRepository.findAll();
    }
}
