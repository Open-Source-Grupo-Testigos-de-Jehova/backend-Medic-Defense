package com.medicdefense.backend.consultation.domain.model.commands;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;

public record AskLegalIssueCommand (String title, LegalConsultation legalConsultationId, String firstMessage) {
}
