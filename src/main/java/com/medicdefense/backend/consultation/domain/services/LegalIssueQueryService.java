package com.medicdefense.backend.consultation.domain.services;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalIssue;
import com.medicdefense.backend.consultation.domain.model.entities.MessageItem;
import com.medicdefense.backend.consultation.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface LegalIssueQueryService {
    Optional<LegalIssue> handle(GetLegalIssueByIdQuery query);
    List<LegalIssue> handle(GetLegalIssuesByConsultationIdQuery query);
    List<LegalIssue> handle(GetAllLegalIssuesQuery query);
    Optional<List<MessageItem>> handle(GetMessageItemsByIdQuery query);
    Optional<MessageItem> handle(GetMessageItemByLegalIssueIdAndMessageIdQuery query);
    Optional<MessageItem> handle(GetLastMessageItemByLegalIssueIdQuery query);
}