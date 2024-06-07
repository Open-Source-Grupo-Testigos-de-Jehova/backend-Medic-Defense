package com.medicdefense.backend.consultation.application.internal.queryservices;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalIssue;
import com.medicdefense.backend.consultation.domain.model.entities.MessageItem;
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
    public Optional<LegalIssue> handle(GetLegalIssueByIdQuery query) {
        return legalIssueRepository.findById(query.legalIssueId());
    }

    @Override
    public List<LegalIssue> handle(GetLegalIssuesByConsultationIdQuery query) {
        return legalIssueRepository.findAllByLegalConsultation_Id(query.consultationId());
    }

    @Override
    public List<LegalIssue> handle(GetAllLegalIssuesQuery query) {
        return legalIssueRepository.findAll();
    }

    @Override
    public Optional<List<MessageItem>> handle(GetMessageItemsByIdQuery query) {
        return legalIssueRepository.findById(query.legalIssueId()).map(legalIssue -> legalIssue.getMessages().getMessageItems());
    }

    @Override
    public Optional<MessageItem> handle(GetMessageItemByLegalIssueIdAndMessageIdQuery query) {
        return legalIssueRepository.findById(query.legalIssueId()).map(legalIssue -> legalIssue.getMessages().getMessageItemById(query.messageItemId()));
    }

    @Override
    public Optional<MessageItem> handle(GetLastMessageItemByLegalIssueIdQuery query) {
        return legalIssueRepository.findById(query.legalIssueId()).map(legalIssue -> legalIssue.getMessages().getLastMessageItem());
    }
}
